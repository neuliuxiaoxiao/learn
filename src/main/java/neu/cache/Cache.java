package neu.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Cache {
	// ��ֵ�Լ���
	private final static Map<String, Entity> map = new HashMap<>();
	//��ʱ���̳߳أ�����������ڻ���
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public synchronized static void put(String key, Object data) {
        Cache.put(key, data, 0);
    }
    public synchronized static void put(final String key, Object data, long expire) {
        //���ԭ��ֵ��
        Cache.remove(key);
        //���ù���ʱ��
        if (expire > 0) {
            Future future = executor.schedule(new Runnable() {
                @Override
                public void run() {
                    //���ں�����ü�ֵ��
                    synchronized (Cache.class) {
                        map.remove(key);
                    }
                }
            }, expire, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(data, future));
        } else {
            //�����ù���ʱ��
            map.put(key, new Entity(data, null));
        }
    }
    public synchronized static Object get(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }
    public synchronized static <T> T get(String key, Class<T> clazz) {
        return clazz.cast(Cache.get(key));
    }
    public synchronized static Object remove(String key) {
        //���ԭ��������
        Entity entity = map.remove(key);
        if (entity == null) {
            return null;
        }
        //���ԭ��ֵ�Զ�ʱ��
        Future future = entity.getFuture();
        if (future != null) {
            future.cancel(true);
        }
        return entity.getValue();
    }
    public synchronized static int size() {
        return map.size();
    }
	/**
	 * ����ʵ����
	 */
	private static class Entity {
		// ��ֵ�Ե�value
		private Object value;
		// ��ʱ��Future
		private Future future;
		public Entity(Object value, Future future) {
			this.value = value;
			this.future = future;
		}
		public Object getValue() {
			return value;
		}
		public Future getFuture() {
			return future;
		}
	}
}
