var mongodb = require("mongodb");
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors')
const moment = require("moment");
const _=require('lodash')
var client = mongodb.MongoClient;
let app = express();
app.use(cors())
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
var url = "mongodb://localhost:27017/";
client.connect(url, function (err, client)

 {
  var db = client.db("POSITION_FLOTTE");
  var positiondb = db.collection("position");

//***********************************API GET itinirary****************************************** ********************************************************************************/

    app.get("/positions", async function (req, res) {
      //console.log('api get work..............')
      const {id,start,end}=req.query
      /*query Mongodb */
          const start2=start.split(" ")
    const end2=end.split(" ")
    
      /*query Mongodb */
          var mngquery = {
              "deviceTime": {
                "$gte": moment(start2[0]).toDate(),
                "$lt":moment(end2[0]).toDate()
              }, "deviceID": id
          };
          /* Projection */
          var projection = {
              "latitude": 1.0,
              "longitude": 1.0,
              "_id":0.0
           };
          //sort
           var sort = [ ["deviceTime", 1.0] ];
           var cursor = await positiondb.find(mngquery).project(projection).sort(sort).toArray();
           /*function remove dupliqua */
           function arrUnique(arr) {
            var cleaned = [];
            arr.forEach(function(itm) {
                var unique = true;
                cleaned.forEach(function(itm2) {
                    if (_.isEqual(itm, itm2)) unique = false;
                });
                if (unique)  cleaned.push(itm);
            });
            return cleaned;
        }

       
        res.status(200).json(arrUnique(cursor));
          console.log("API executed")
        
         
        });
        //***********************************API GET last positions of all vehicles****************************************** ********************************************************************************/



// Socket.io for real-time updates (optional)

});

let server = app.listen(2222, function() {
    console.log('Server is listening on port 2222')
});


//localhost:2222/lastposition/359633104333039                                                       lastposition
//localhost:2222/position?id=354018114581451&start=2022-03-08T00:10&end=2022-03-10T15:10             historiq
//localhost:2222/traget?id=354018114581451&start=2022-03-08T00:10&end=2022-03-10T15:10              traget optimisé
//localhost:2222/localite/?lon=-9.647621&lat=30.787528                                               localite


