(ns re-frame-template.config)

;; Defines the verbosity. Will be overriden by
;;  closure-defines during compilation.
(goog-define verbose? false)

;; Defines the dev or prod environment Will be overriden by
;;  closure-defines during compilation.
(goog-define dev? false)

;; Defines if the application is in debug mode.
(def debug?
  ^boolean goog.DEBUG)
