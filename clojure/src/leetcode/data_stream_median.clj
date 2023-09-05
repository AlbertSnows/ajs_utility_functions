(ns data-stream-median
  (:import [java.util PriorityQueue Comparator]))

  
;; The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

;;     For example, for arr = [2,3,4], the median is 3.
;;     For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

;; Implement the MedianFinder class:

;;     MedianFinder() initializes the MedianFinder object.
;;     void addNum(int num) adds the integer num from the data stream to the data structure.
;;     double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

 

;; Example 1:

;; Input
;; ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
;; [[], [1], [2], [], [3], []]
;; Output
;; [null, null, null, 1.5, null, 2.0]

;; Explanation
;; MedianFinder medianFinder = new MedianFinder();
;; medianFinder.addNum(1);    // arr = [1]
;; medianFinder.addNum(2);    // arr = [1, 2]
;; medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
;; medianFinder.addNum(3);    // arr[1, 2, 3]
;; medianFinder.findMedian(); // return 2.0

(defn make-median-finder [] {:large (PriorityQueue.)
                             :small (PriorityQueue. (comparator >))})

(defn adjust-queues [small-too-big? too-small too-big]
  (let [value-to-swap (.poll too-big)
        _ (.add too-small value-to-swap) 
        answer (if small-too-big? 
                 {:small too-big :large too-small} 
                 {:small too-small :large too-big})]
    answer))

(defn adjust-if-needed [small large]
  (let [small-size (.size small)
        large-size (.size large)
        diff (Math/abs (- small-size large-size))
        needs-resized? (= 2 diff)
        too-many-smalls? (> small-size large-size)
        result (cond
                 (not needs-resized?) {:small small :large large}
                 too-many-smalls? (adjust-queues true large small)
                 :else (adjust-queues true small large))
        answer result]
    answer))

(defn add-num-to-median-finder [{:keys [small large]} num]
  (let [largest-small (if (nil? (.peek small)) -1 (.peek small))
        smallest-large (if (nil? (.peek large)) -1 (.peek large))
        less-than-largest-small? (>= largest-small num)
        greater-than-smallest-large? (>= num smallest-large)
        between-both? (and (not less-than-largest-small?) (not greater-than-smallest-large?))
        _ (when (or less-than-largest-small? between-both?) (.add small num))
        _ (when greater-than-smallest-large? (.add large num))
        answer (adjust-if-needed small large)]
    answer))

(defn find-median [{:keys [small large]}]
  (let [small-size (.size small)
        large-size (.size large)
        largest-small (.peek small)
        smallest-large (.peek large)
        equal-size? (= small-size large-size)
        small-has-more-elements? (> small-size large-size)
        answer (cond 
                 small-has-more-elements? largest-small
                 (not equal-size?) smallest-large
                 :else (/ (+ largest-small smallest-large) 2))]
    answer))

(comment
  
  (let [median-finder (make-median-finder)
        _ (add-num-to-median-finder median-finder 1)
        _ (add-num-to-median-finder median-finder 2)
        median-one (find-median median-finder)
        _ (add-num-to-median-finder median-finder 3)
        median-two (find-median median-finder)
        answer [median-one median-two]]
  answer)

  (let [x (PriorityQueue.)
        _ (.add x 1)
        _ (.add x 2)
        b (.poll x)
        r [x b]]
    r)
  ;; medianFinder.addNum(1);    // arr = [1]
;; medianFinder.addNum(2);    // arr = [1, 2]
;; medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
;; medianFinder.addNum(3);    // arr[1, 2, 3]
;; medianFinder.findMedian(); // return 2.0



  )