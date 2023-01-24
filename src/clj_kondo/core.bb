(ns clj-kondo.core
  (:refer-clojure :exclude [run!])
  (:require [babashka.pods :as pods]))

(pods/load-pod 'clj-kondo/clj-kondo "2023.01.20")

(require '[pod.borkdude.clj-kondo :as kondo])

(def run! kondo/run!)

(def print! kondo/print!)

(defn exec
  {:org.babashka/cli
   {:coerce {:lint [:string]
             :config :edn}}}
  ;; TODO: add more coercions
  [m]
  (-> (run! m) print!))
