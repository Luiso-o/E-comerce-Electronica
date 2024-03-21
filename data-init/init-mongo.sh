#!/bin/bash
mongoimport --uri="mongodb://localhost:27017/pcstore" --collection users --type json --file /data-init/pcstore_db.user.json --jsonArray
mongoimport --uri="mongodb://localhost:27017/pcstore" --collection products --type json --file /data-init/pcstore_db.Products.json --jsonArray
