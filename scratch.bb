(require '[clj-kondo.core :as clj-kondo])

(let [{:keys [summary] :as results} (clj-kondo/run! {:lint ["src"]})]
  (clj-kondo/print! results)
  (when (or (pos? (:warning summary))
            (pos? (:error summary)))
    (throw (ex-info "Lint errors" {:babashka/exit 1}))))
