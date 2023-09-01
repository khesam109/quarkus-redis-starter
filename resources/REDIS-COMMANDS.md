# Redis Command:

- **Run server:**  
  `redis-server`
- **Run client:**  
  `redis-cli`
- **Shutdown client:**  
  `quit`
- **Ping Server:**  
  `ping`
- **Shutdown server:**  
  `shutdown`
- **Get server info:**
  `info`
----------
- **Store a tuple:**  
  `set [key] [value]`
- **Store a tuple if key not exist:**  
  `setnx [key] [value]`
- **Store multiple tuples:**  
  `mset ([key] [value])+`
- **Store multiple tuples if key not exist:**  
  `msetnx ([key] [value])+`
- **Store a tuple with expiration:**  
  `set [key] [value] ['ex'|'px'] [expiration-value]`
- **Store a tuple with expiration(seconds):**  
  `setex [key] [expiration-value] [value]`
- **Store a tuple with expiration(milli second):**  
  `psetex [key] [expiration-value] [value]`
- **Store a tuple and get the old value:**  
  `getset [key] [value]`
- **Retrieve a tuple:**  
  `get [key]`
- **Retrieve multiple tuples:**  
  `mget ([key])+`
- **Delete a key synchronously(blocking):**  
  `del ([key])+`
- **Delete a key asynchronously(non blocking):**  
  `unlink ([key])+`
- **Check a key presence:**  
  `exists ([key])+`
- **Check the remaning ttl for a key(second):**  
  `ttl [key]`
- **Check the remaning ttl for a key(millisecond):**  
  `pttl [key]`
- **Remove the expiration from a key:**  
  `persist [key]`
- **Rename a key to new-key:**  
  `rename [key] [new-key]`
- **Rename a key to new-key if new-key does not exist:**  
  `renamenx [key] [new-key]`

- **Get the type of the value of a key:**  
  `type [key]`
- **Get the value enconding:**  
  `object encoding [key]`

- **Select a key space:**  
  `select [index]`
- **Get all keys within a key space:**  
  `keys [pattern]`

- **Increase by one the value of a key (value type can be strings but should be integer):**  
  `incr [key]`
- **Decrease by one the value of a key (value type can be strings but should be integer):**  
  `decr [key]`
- **Increase the value of a key (value type can be strings but should be integer):**  
  `incrby [key] [increment]`
- **Decrease the value of a key (value type can be strings but should be integer):**  
  `decrby [key] [increment]`

- **Get string length:**  
  `strlen [key]`
- **Append a value at the end of the string:**  
  `append [key] [value]`

- **Generate sample data for test:**  
  `debug poplulate [number-of-tuples]`

- **Add elements to the end of a list:**  
  `rpush [key] ([value])+`
- **Add elements to the start of a list:**  
  `lpush [key] ([value])+`
- **Get elements of a list:**  
  `lrange [key] [start] [end]`
- **Get element at a specific index of a list:**  
  `lindex [key] [index]`