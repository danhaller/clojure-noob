(ns fwpd.core
  (:require [clojure.string :as s]))

(def filename "src/fwpd/suspects.csv")
(def headers->keywords {"Name" :name "Glitter Index" :glitter-index})
(defn str->int [str] (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn parse
  [string]
  (map #(s/split % #",")
    (s/split-lines string)))

(defn mapify-row
  [headers unmapped-row]
  (into {}
    (map (fn [header column]
            [header ((get conversions header) column)])
          headers
          unmapped-row)))

(defn mapify
  [rows]
  (let [
        headers (map #(get headers->keywords %) (first rows))
        unmapped-rows (rest rows)]
    (map (partial mapify-row headers) unmapped-rows)))


(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))


(defn get-vampire-names
  [vampires]
  (map #(get % :name) vampires))

(defn prepend
    [suspects new-suspect]
    (conj suspects new-suspect))

(def not-blank (complement s/blank? ))
(def suspect-rules {:name not-blank :glitter-index not-blank})

(defn check-rule
  [[keyword predicate] subject]
  (println keyword predicate subject)
  (predicate (get subject keyword)))

(defn validate
  [rules new-suspect]
  (every? #(check-rule % new-suspect) rules))

(validate suspect-rules {:name "Dave", :glitter-index "6"})



(get-vampire-names(glitter-filter 3 (prepend(mapify (parse (slurp filename))) {:name "Dave" :glitter-index 7})))
