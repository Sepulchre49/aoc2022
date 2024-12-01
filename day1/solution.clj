(ns aoc2022.day1
  (:require [clojure.string :as str])
  (:import (java.io File)))

(def elves 
      (map #(str/split % #"\n")
           (str/split (slurp "input.txt") #"\n\n")))

(def snacks (map #(reduce + (map Integer/parseInt %)) elves))

(defn nth-largest 
  ; Initial call
  ([n numbers] (nth-largest n numbers nil))
  ; Recursive helper
  ([n numbers result]
   (if (= n 0)
     result
     (let [maximum (apply max numbers)
           freq_map (frequencies numbers)
           freq_max (get freq_map maximum)]
       (recur 
         (max (- n freq_max) 0)
         (filter #(not (= maximum %)) numbers)
         (concat result (repeat (min freq_max n) maximum)))))))

(apply + (nth-largest 3 snacks))
