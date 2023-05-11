# JBomberman
mdp project



info:
prima di tutto installa git, basta che cerchi git download e lo trovi.
per lavorare sul progetto sul tuo pc nella pagina web  di questa repository clicca il tasto "clone or download" e copia l'url https. apri intellij e nella schermata in cui ti chiede 
che progetto vuoi aprire, clicca su "get from VCS" in alto a destra. ora scegli la directory dove vuoi mettere il progetto, incolla l'url nella sezione url e clicca su 
clone.
in teoria ti dovresti trovare nel branch Master. Se premi Alt+9 dovrebbe aprirsi il menù di git, da qui puoi vedere i branch e i commit fatti. per creare un branch clicca
con il tasto destro sul Master sotto la directory local e selezione "new branch from Master". a questo punto clicca con il tasto destro sul branch che hai creato e seleziona 
checkout, oppure da terminele di intellij scrivi "git branch nomeBranch". a questo punto fai le modifiche che vuoi. Per aggiungerle devi cliccare sul nome del progetto, poi 
devi premere il tasto destro, andarte sul menù di git e selezionare "add" poi sempre dallo stesso menù seleziona "commit", scrivi un messaggio per dire che cosa hai modificato
clicca su commit and push, oppure clicca su commit e poi riapri il menù di git e selezioni push.
forse durante il push ti chiederà di loggarti con le credenziali di github.
Per mettere le modifiche che hai fatto nel branch Master, per prima cosa posizionati nel branch Master poi clicca col tasto destro sul branch da cui vuoi prendere le modifiche 
e seleziona "merge nomeBranch into Master" e poi fai add, commit e push. Forse potrebbe non rilevare che ci sono stati cambiamenti quindi cambia leggermente qualcona e poi 
rifai add, commit e push. Quando fai il merge di un branch intellij ti chiede se vuoi cancellare quel branch.
per vedere le modifiche su github, se lo tenevi già aperto devi aggiornare la pagina. 
il branch di default della repository è master, quindi le tue modifiche le vedrai sul branch Master, per metterle nel branch di default deevi fare una pull request.
quindi clicci su pull request, poi su new pull request, lasci come base "master" e come compare metti "Master", a questo punto selezioni il commit da mettere in master e confermi la
creazione della pull request. Non so se github ti permetta di confermare la pull request e quindi di modificare il master, quindi probabilmente io devo confermare la tua pull request.


N.B. intellij ha un problema cioè che bisogna specificare manualmente qual è la cartella delle risorse (nel nostro caso res). per farlo una volta aperto il progetto clicca sul nome del progetto prima col tasto sinistro e poi col tasto destro, ora sul menù che si è aperto va su "mark directory as" poi seleziona "source root". a questo punto vai sul menu "file", seleziona "project structure" vai su moduli seleziona la cartella res e premi sul bottone "resources" a questo punto confrema
