(ns taxi-railway-passengers)

(defn search-for-passenger [roadmap row-index]
      (fn [passenger-locations column-index]
        (let [value (get-in roadmap [row-index column-index])
              is-passenger? (= value 1)
              updated-set (if is-passenger?
                            (conj passenger-locations [row-index column-index])
                            passenger-locations)
              answer updated-set]
          answer)))

(defn search-rows [roadmap column-indexes]
  (fn [passenger-locations row-index]
    (let [passengers-for-row (reduce (search-for-passenger roadmap row-index) [] column-indexes)
          updated-passenger-locations (into passenger-locations passengers-for-row)
          answer updated-passenger-locations]
      answer)))
(defn get-right [step] [(get step 0) (inc (get step 1))])
(defn get-down [step] [(inc (get step 0)) (get step 1)])
(defn setup-should-visit [visited graph] 
  (fn [step] (not (or
                   (contains? visited step)
                   (nil? (get-in graph step))
                   (= -1 (get-in graph step) -1)))))
(defn setup-neighbor-node-accessible? [visited result]
  (fn [step]
    (and
     (contains? visited step)
     (true? (get-in result step)))))
(def get-nodes-to-visit 
  (fn [step visited graph]
    (let [right (get-right step)
          down (get-down step)
          should-visit? (setup-should-visit visited graph)
          visit-right? (should-visit? right)
          visit-down? (should-visit? down)
          neither-visited? (and visit-right? visit-down?)
          both-visited? (and (not visit-right?) (not visit-down?))
          answer (cond 
                   neither-visited? [right down]
                   both-visited? []
                   visit-right? [right]
                   :else [down])]
      answer)))
(defn get-next-steps [visited accessible-map bfs traversal ? graph]
  (fn [step]
    (let [step [0 0]
          right [(get step 0) (inc (get step 1))]
          down [(inc (get step 0)) (get step 1)]
          visited-right? (or (contains? visited right)
                             (nil? (get-in accessible-map right)))
          visited-down? (or (contains? visited down)
                            (nil? (get-in accessible-map down)))
          right-result (when (not visited-right?)
                         (bfs traversal ? graph right accessible-map))
          down-result (when (not visited-down?)
                        (bfs traversal ? graph down accessible-map))
          right-accessible? (= true (get-in accessible-map right) false)
          down-accessible? (= true (get-in accessible-map down) false)
          not-roadblock? (not (= -1 (get-in graph step)))
          current-accessible? (and not-roadblock?
                                   (or right-accessible? down-accessible?))
          updated-accessible-map (assoc-in accessible-map current-accessible? step)
          answer "..."]
      answer)))

(defn bfs [traversal get-next-nodes graph step result visited]
  (let [graph [[0  0]
               [1  0]]
        accessible-map [[false false]
                        [false false]]
        get-next-nodes get-nodes-to-visit
        nodes-to-check (get-next-nodes step visited graph)
        update-current-node (fn [graph step result]
                              (let [current-not-neg? (not (= -1 (get-in graph step)))
                                    right (get-right step)
                                    neighbor-node-accessible? (setup-neighbor-node-accessible? visited result)
                                    right-accessible? (neighbor-node-accessible? right)
                                    down (get-down step)
                                    down-accessible? (neighbor-node-accessible? down) 
                                    accessible? (and current-not-neg? 
                                                     right-accessible? 
                                                     down-accessible?)
                                    updated-result (assoc-in result accessible? step)
                                    answer updated-result]
                                answer))
        updated-result (update-current-node graph step result)
        
        ;; search right 
        ;; search down
        ;; if exit-criteria
        ;; return to parent
        ;; else traverse
        visited #{}
        step [0 0]

        traversal get-next-steps
        answer traversal]
    answer))

(comment

  (let [w 4 ;; w * l
        l 3
        roadmap [[0  0  -1  1]
                 [1  0  -1  0]
                 [-1 -1  0  -1]
                 [0  0  -1  0]
                 [1  0  -1  0]]
        row-indexes (range 0 (count roadmap))
        column-indexes (range 0 (count (first roadmap)))
        accessible-map (vec (repeat l (vec (repeat w false))))
        result accessible-map
        answer accessible-map]
    answer)

graph [[0  0  -1  1]
       [1  0  -1  0]
       [-1 -1  0  -1]
       [0  0  -1  0]
       [1  0  -1  0]]
accessible-map [[false false false false]
                [false false false false]
                [false false false false]]

        ;; passenger-locations (reduce (search-rows roadmap column-indexes)  [] row-indexes)
        ;; locations to check for blocks
        ;; [;; [0 3]
        ;;  ]

(let [map [[0 0 0  1]
           [1 0 -1 0]
           [0 0 0  0]]
      value (get-in map [0 0])
      answer "..."
      ]
  answer)

  )