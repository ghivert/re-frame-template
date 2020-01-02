(ns gimme-my-money.events
  (:require
   [re-frame.core :as re-frame]
   [gimme-my-money.db :as db]
   [gimme-my-money.config :as config]
   [com.degel.re-frame.storage]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-fx ::initialize-db
  [(re-frame/inject-cofx :storage/get {:name :user-id})]
  (fn-traced [cofx]
    (let [db (db/init (:storage/get cofx))]
      (when config/dev?
        (println "User read from localStorage")
        {:db db}))))

(re-frame/reg-event-db ::navigate-to
  (fn-traced [db [_event active-page]]
    (assoc db :active-page active-page :menu-opened false)))

(re-frame/reg-event-db ::open-menu
  (fn-traced [db]
    (assoc db :menu-opened (not (:menu-opened db)))))

(re-frame/reg-event-db ::log-in
  (fn-traced [db [_event user-id]]
    (assoc db :user user-id)))

(re-frame/reg-event-fx ::log-in
  (fn-traced [cofx [_event user-id]]
    {:storage/set {:session? false
                   :name :user-id
                   :value user-id}
     :dispatch [::log-in user-id]}))
