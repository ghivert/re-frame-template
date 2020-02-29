(ns router.re-frame
  (:require [re-frame.core :as re-frame]
            [router.core]))

(re-frame/reg-fx :navigate-to router.core/navigate-to!)
