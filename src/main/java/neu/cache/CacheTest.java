package neu.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CacheTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String key = "id";
		// �����ù���ʱ��
		System.out.println("***********�����ù���ʱ��**********");
		Cache.put(key, 123);
		System.out.println("key:" + key + ", value:" + Cache.get(key));
		System.out.println("key:" + key + ", value:" + Cache.remove(key));
		System.out.println("key:" + key + ", value:" + Cache.get(key));

		// ���ù���ʱ��
		System.out.println("\n***********���ù���ʱ��**********");
		Cache.put(key, "123456", 1000);
		System.out.println("key:" + key + ", value:" + Cache.get(key));
		Thread.sleep(2000);
		System.out.println("key:" + key + ", value:" + Cache.get(key));

		/****************** �������ܲ��� ************/
		System.out.println("\n***********�������ܲ���************");
		// ������10���̵߳��̳߳أ���1000000�β�����10����ӵ��̳߳�
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		Future[] futures = new Future[10];
		/******** ��� ********/
		{
			long start = System.currentTimeMillis();
			for (int j = 0; j < 10; j++) {
				futures[j] = executorService.submit(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i < 100000; i++) {
							Cache.put(Thread.currentThread().getId() + key + i, i, 300000);
						}
					}
				});
			}
			// �ȴ�ȫ���߳�ִ����ɣ���ӡִ��ʱ��
			for (Future future : futures) {
				future.get();
			}
			System.out.printf("��Ӻ�ʱ��%dms\n", System.currentTimeMillis() - start);
		}

		/******** ��ѯ ********/
		{
			long start = System.currentTimeMillis();
			for (int j = 0; j < 10; j++) {
				futures[j] = executorService.submit(() -> {
					for (int i = 0; i < 100000; i++) {
						Cache.get(Thread.currentThread().getId() + key + i);
					}
				});
			}
			// �ȴ�ȫ���߳�ִ����ɣ���ӡִ��ʱ��
			for (Future future : futures) {
				future.get();
			}
			System.out.printf("��ѯ��ʱ��%dms\n", System.currentTimeMillis() - start);
		}

		System.out.println("��ǰ����������" + Cache.size());
	}
}
