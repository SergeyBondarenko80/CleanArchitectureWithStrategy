The app architecture is based on google seed project "Android Architecture Blueprints [beta] - MVP + Clean Architecture".
However in during mobile applications development it was found that the most popular MVP and other SOLID seed projects
for mobile apps development break "I" letter "Interface segregation principle" of SOLID design, especially different 
elements of network layer which usually contains parse utils, REST requests logics and other useful network functions.
All of that is usually stored in a couple of huge files. To solve this law-breaking moments we use strategy pattern which
brings dynamic behavior for network layer. This helps to keep "I" principle as well as "S" Single responsibility principle.

There are many ways of communicating of presenter with different application layers. For simlpe REST funcitons direct connection
"presenter - datasource" can be used as we show in "sign_in" package. But for offline applicaitons which include persistence data
layer the connection "presenter - repository - datasource" is preffered, so we made sign_up communicating through this layer to
display this ability. As is known the repository can include several data sources, f.e. network data source and persistence database
data source. As well the repository can include logics of different data sources use cases.