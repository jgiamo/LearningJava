package algorithm;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;
import java.util.Random;

public class Sort {

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static int[] mergeSort(int[] array, int startPos, int endPos) {
        if (startPos >= endPos) {
            return array;
        }
        int middlePos = (startPos + endPos) / 2;
        int[] leftArr = mergeSort(array, startPos, middlePos);
        int[] rightArr = mergeSort(array, middlePos + 1, endPos);

        int[] mergeArr = new int[endPos - startPos + 1];
        int pos = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                mergeArr[pos] = leftArr[i];
                i++;
            } else {
                mergeArr[pos] = rightArr[j];
                j++;
            }
            pos++;
        }
        while (i < leftArr.length) {
            mergeArr[pos++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            mergeArr[pos++] = rightArr[j++];
        }

        for (i = startPos, pos = 0; i <= endPos; ) {
            array[i++] = mergeArr[pos++];
        }
        return mergeArr;

    }

    public static void quickSort(int[] array) {

    }

    /**
     * 选择排序，升序排序
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minPos = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPos]) {
                    minPos = j;
                }
            }
            int tmp = array[minPos];
            array[minPos] = array[i];
            array[i] = tmp;
        }

        return;
    }

    /**
     * 插入排序，升序排序
     * 稳定性：符合，因为比较元素时，相等的元素排在后面
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = 0;
            for (; j < i; j++) {
                if (array[i] < array[j]) {
                    break;
                } else {
                    continue;
                }
            }

            int tmp = array[i];
            for (int k = i; k >= j + 1; k--) {
                array[k] = array[k - 1];
            }
            array[j] = tmp;
        }

        return;
    }

    /**
     * 冒泡排序，升序排序
     * 稳定性：符合，因为如果相等，不交换位置
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }

        return;
    }

    public static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void headipfy(int[] array, int currentPos, int len) {
        //建立大顶堆，从倒数第二层非叶子节点开始，往下比较
        while (true) {
            int left = 2 * currentPos + 1;
            int right = 2 * currentPos + 2;
            int max = currentPos;
            if (left < len && array[max] < array[left]) {
                max = left;
            }
            if (right < len && array[max] < array[right]) {
                max = right;
            }
            if (max != currentPos) {
                swap(array, currentPos, max);
            } else {
                break;
            }
            currentPos = max;
        }
    }

    /**
     * 堆排序
     * @param array
     */
    public static void heapSort(int[] array) {
        int len = array.length;
        for (int i = len -1 ; i >= 0; i--) {
            headipfy(array, i, len);
        }
        for (int i = len - 1; i >= 0; i--) {
            swap(array, 0, i);
            headipfy(array, 0, i);
        }

    }

    public static final int ARRAY_SIZE = 100;

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());


        int[] sortArray = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            sortArray[i] = random.nextInt(100);
        }

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int [] mySortArray = sortArray.clone();
//                Long startTime = System.currentTimeMillis();
//                bubbleSort(mySortArray);
//                Long stopTime = System.currentTimeMillis();
//                System.out.println("bubbleSort:" + (stopTime - startTime));
//            }
//        });
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int [] mySortArray = sortArray.clone();
//                Long startTime = System.currentTimeMillis();
//                insertSort(mySortArray);
//                Long stopTime = System.currentTimeMillis();
//                System.out.println("insertSort:" + (stopTime - startTime));
//            }
//        });
//
//        Thread thread3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int [] mySortArray = sortArray.clone();
//                Long startTime = System.currentTimeMillis();
//                selectSort(mySortArray);
//                Long stopTime = System.currentTimeMillis();
//                System.out.println("selectSort:" + (stopTime - startTime));
//            }
//        });
//
//
//        thread1.start();
//        thread2.start();
//        thread3.start();


//        System.out.println(Arrays.toString(sortArray));


//        System.out.println("sorting Result");
//        bubbleSort(sortArray);
//        insertSort(sortArray);
//        selectSort(sortArray);
//        mergeSort(sortArray);
        heapSort(sortArray);
        System.out.println(Arrays.toString(sortArray));


    }
}
