(ns gimme-my-money.db)

(def teams
  [{:id "0"
    :picture nil
    :title "Design Team"
    :collaborators ["1" "2" "3"]}
   {:id "10"
    :picture nil
    :title "Dev Team"
    :collaborators ["1" "2" "3"]}])

(def collaborators
  [{:id "1"
    :picture "http://lorempixel.com/400/400/people"
    :first-name "Guillaume"
    :last-name "Hivert"}
   {:id "2"
    :picture "http://lorempixel.com/400/400/people"
    :first-name "Agathe"
    :last-name "Machavoine"}
   {:id "3"
    :picture "http://lorempixel.com/400/400/people"
    :first-name "Camille"
    :last-name "Laurent"}])

(def projects
  [{:id "7"
    :title "Facepoint"
    :objective "Redesign du site vitrine"
    :description "Redesigner le site de Facepoint prends du temps mais est hautement gratifiant"
    :files []
    :collaborators ["1" "2" "3"]
    :status :pending}])

(defn init [user-id]
  {:user user-id
   :active-page :home-page
   :teams teams
   :projects projects
   :collaborators collaborators})
