(ns re-frame-template.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::active-page
  (fn [db]
    (:active-page db)))
