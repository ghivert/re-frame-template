(ns re-frame-template.events
  (:require
   [re-frame.core :as re-frame]
   [re-frame-template.db :as db]
   [re-frame-template.config :as config]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db ::initialize-db
  (fn-traced [db_]
    (let [db (db/init)]
      {:db db})))

(re-frame/reg-event-db ::navigate-to
  (fn-traced [db [_event active-page]]
    (assoc db :active-page active-page)))
