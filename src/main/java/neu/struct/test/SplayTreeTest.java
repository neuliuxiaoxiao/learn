package neu.struct.test;

import neu.struct.SplayTree;
import neu.struct.SplayTree2;
import neu.struct.SplayTree.SplayTreeNode;

/**
 * Java 语言: 伸展树
 *
 * @author skywang
 * @date 2014/02/03
 */
public class SplayTreeTest {

    private static final int arr[] = {10,50,40,30,20,60};

    public static void main(String[] args) {
    	test2();
       
    }
    private void test1(){
    	 int i, ilen;
         SplayTree<Integer> tree=new SplayTree<Integer>();

         System.out.print("== 依次添加: ");
         ilen = arr.length;
         for(i=0; i<ilen; i++) {
             System.out.print(arr[i]+" ");
             tree.insert(arr[i]);
         }

         System.out.print("\n== 前序遍历: ");
         tree.preOrder();

         System.out.print("\n== 中序遍历: ");
         tree.inOrder();

         System.out.print("\n== 后序遍历: ");
         tree.postOrder();
         System.out.println();

         System.out.println("== 最小值: "+ tree.minimum());
         System.out.println("== 最大值: "+ tree.maximum());
         System.out.println("== 树的详细信息: ");
         tree.print();

         i = 30;
         System.out.printf("\n== 旋转节点(%d)为根节点\n", i);
         tree.splay(i);
         System.out.printf("== 树的详细信息: \n");
         tree.print();

         // 销毁二叉树
         tree.clear();
    }
    private static void test2(){
    	int arr[] = {8,4,30,2,6,9,38,1,3,5,7,33,39,31,34};
        SplayTree2<Integer> splayTree = new SplayTree2<Integer>();
        //进行插入
        for (int anArr : arr) {
            splayTree.insert(anArr);
        }
        //打印树
        System.out.println("root:" + splayTree.getRoot());
        System.out.println("当前树的中序遍历如下：");
        splayTree.inOrder();
        System.out.println();

        //进行搜索节点33
        splayTree.search(33);
        System.out.println("root:" + splayTree.getRoot());
        System.out.println("当前树的中序遍历如下：");
        splayTree.inOrder();
        System.out.println();

        //进行删除节点8
        splayTree.remove(8);
        System.out.println("root:" + splayTree.getRoot());
        System.out.println("当前树的中序遍历如下：");
        splayTree.inOrder();
        System.out.println();
    }
}
