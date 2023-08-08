const cluster = require("cluster");
var numCPUs = require('os').cpus().length;
if (cluster.isMaster) {
  for (let i = 0; i < numCPUs; i++) {
    cluster.fork();
    
  }
  cluster.on('exit', (worker, code, signal) => {
	cluster.fork();
  
});
} else {
   
  require("./index.js");
  
}