(ns metadata)

(defn add-key-metadata [content]
  (let [adder (fn [index item]
                (if (string? item) item
                  (with-meta item {:key index})))]
    (map-indexed adder content)))
