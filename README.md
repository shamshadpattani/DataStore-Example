# DataStore-Example
DataStore is a storage solution which is built on Coroutines and Flow to store data asynchronously. 
DataStore a replacement for SharedPreferences

# What is data store

     Jetpack DataStore is a data storage solution. It allows us to store key-value pairs (like SharedPreferences) 
     or typed objects with protocol buffers. DataStore uses Kotlin 
     and Coroutines + Flow to store data synchronously with consistency and transaction support 
# Two Type
  ## 1 Preferences DataStore
       stores and accesses data using keys. 
       This implementation does not require a predefined schema, and it does not provide type safety.
   ## 2 Proto DataStore 
       stores data as instances of a custom data type.
       This implementation requires you to define a schema using protocol buffers, but it provides type safety.
