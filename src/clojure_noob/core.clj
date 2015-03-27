(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Teapot"))

(defn train []
  (println "Choo choo!"))


(if false
  (do (println "success")
    "abra")
  (do (println "failure")
    "hocus"))


(when true (println "Success!")
  "abra")

(def failed-protagonist-names ["Larry Potter", "Doreen", "Incredible Bulk"])

(nil? 1)
(nil? nil)
(= 1 1)

(str "hello" " " "world!")

{ :a 1
  :b "boring example"
  :c []}

{ "string-key" + }

(get { :a 55} :a)

(get { :a 100} :b "nope")

(get-in { :a { :b "hello"} } [:a :b])

({:a "The Human Coffee Pot"} :a)

(:a { :a "The Human Coffee Pot2"})

(:d {:a 1 :b 2 :c 3} "nope")

(hash-map :a 1 :b 2)

(get [3 2 1] 0)

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1)

(vector "creepy" "full" "moon")

(conj [1 5 7] 10)

'(1 2 3 4)

(nth '(1 2 3 4) 2)

(list 1 2 3 4)

#{ "hannah montana" "miley cyrus" 20 25}

(conj #{:a :b} :b)

(get #{:a :b} :a)

(:a #{:a :b})

(set [3 3 3 4 4])


(get(set [3 3 3 4 4]) 3)
(get(set [3 3 3 4 4]) 9)

(sorted-set "a" "c" "q" "b")

(def failed-movie-titles ["Gone With the Moving Air" "Swellfellas"])

(first failed-protagonist-names)

(first '(failed-protagonist-names 0 1))

((or + -) 1 2 3)

(inc 1.1)

(map inc [0 1 2 3])

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
  "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY TO SOMEWHERE"))

(doc too-enthusiastic)

(defn multi-arity
  ([first-arg second-arg third-arg]
    (do-things first-arg second-arg third-arg))
  ([first-arg second-arg]
    (do-things first-arg second-arg))
  ([first-arg]
    (do-things first-arg)))

(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
    (str "I " chop-type " chop " name "! Take that!"))
  ([name] (x-chop name "karate")))

(x-chop "Kanye West" "slap")
(x-chop "Kanye East")

(defn codger-communication
  [whippersnapper]
  (str "get off my lawn, " whippersnapper))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "bob" "dave" "fred")

(defn my-first
  [[first-thing]]
  first-thing)

(my-first ["oven" "bike" "waraxe"])

(defn chooser
  [[first-choice second-choice & rest]]
  (println (str "first choice:" first-choice))
  (println (str "second choice:" second-choice))
  (println (str "other choices:" (clojure.string/join ", " rest))))

(chooser ["bob" "dave" "fred" "steve" "rufus"])

(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure long: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})

(defn announce-treasure-location-two
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure long: " lng)))

(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function)

(map
  (fn [name] (str "Hi, " name))
  ["Darth Vader" "Mr Magoo"])

(map
  #(str "Hi, " %) ["Darth Vader", "Magoo"])

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)

(def asym-hobbit-body-parts [
  {:name "head" :size 3}
  {:name "left-eye" :size 1}
  {:name "left-ear" :size 1}
  {:name "mouth" :size 1}
  {:name "nose" :size 1}
  {:name "neck" :size 2}
  {:name "left-shoulder" :size 3}
  {:name "left-upper-arm" :size 3}
  {:name "chest" :size 10}
  {:name "back" :size 10}
  {:name "left-forearm" :size 3}
  {:name "abdomen" :size 6}
  {:name "left-kidney" :size 1}
  {:name "left-hand" :size 2}
  {:name "left-knee" :size 2}
  {:name "left-thigh" :size 4}
  {:name "left-lower-leg" :size 3}
  {:name "left-achilles" :size 1}
  {:name "left-foot" :size 2}
  ])

(defn needs-matching-part? [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part [part]
  { :name (clojure.string/replace (:name part) #"^left-" "right-")
    :size (:size part)})

(needs-matching-part? (get asym-hobbit-body-parts 1))

(defn symmetrize-body-parts
  "Expects a sequence of maps with have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
    final-body-parts []]
    (if (empty? remaining-asym-parts)
    final-body-parts
    (let [[part & remaining] remaining-asym-parts
      final-body-parts (conj final-body-parts part)]
      (if (needs-matching-part? part)
      (recur remaining (conj final-body-parts (make-matching-part part)))
      (recur remaining final-body-parts))))))

(symmetrize-body-parts asym-hobbit-body-parts)
(reduce + [1 2 3 4])
(reduce + 15 [1 2 3 4])

(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
    (let [final-body-parts (conj final-body-parts part)]
      (if (needs-matching-part? part)
      (conj final-body-parts (make-matching-part part))
      final-body-parts)))
      []
      asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
      body-part-size-sum (reduce + 0 (map :size sym-parts))
      target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-parts accumulated-size (:size part)]
      (if (> accumulated-size target)
      part
      (recur rest (+ accumulated-size (:size part)))))))

(hit asym-hobbit-body-parts)