(ns clj-kondo.core
  (:refer-clojure :exclude [run!])
  (:require [babashka.pods :as pods]))

(pods/load-pod 'clj-kondo/clj-kondo "2024.05.24")

(require '[pod.borkdude.clj-kondo :as clj-kondo])

(def run! clj-kondo/run!)

(def print! clj-kondo/print!)

(defn exec
  {:org.babashka/cli
   {:coerce {:lint [:string]
             :config :edn}}}
  ;; TODO: add more coercions
  [m]
  (let [{:keys [summary] :as results} (clj-kondo/run! m)]
    (clj-kondo/print! results)
    (when (or (pos? (:warning summary))
              (pos? (:error summary)))
      (throw (ex-info "Lint errors" {:babashka/exit 1})))))
