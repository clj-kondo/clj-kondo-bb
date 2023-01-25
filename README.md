# clj-kondo.bb

Invoke [clj-kondo](https://github.com/clj-kondo/clj-kondo) from babashka scripts!

## Install

Add this dependency to `bb.edn` or `deps.edn`:

``` clojure
{:deps {io.github.clj-kondo/clj-kondo.bb {:git/tag "v2023.01.20" :git/sha "bf0d878"}
```

You can invoke clj-kondo as a babashka one-liner like this:

``` clojure
bb -Sdeps '{:deps {io.github.clj-kondo/clj-kondo.bb ...}}' -x clj-kondo.core/exec --lint src
```

You can add this library along with JVM clj-kondo without causing conflicts.

## Usage

In a babaska script:


``` clojure
(require '[clj-kondo.core :as clj-kondo])

(let [{:keys [summary] :as results} (clj-kondo/run! {:lint ["src"]})]
  (clj-kondo/print! results)
  (when (or (pos? (:warning summary))
            (pos? (:error summary)))
    (throw (ex-info "Lint errors" {:babashka/exit 1}))))
```

## License

Copyright © 2019 - 2023 Michiel Borkent

Distributed under the EPL License, same as Clojure. See LICENSE.
