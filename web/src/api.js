const express = require('express');
const serverless = require('serverless-http');

const app = express();
const router = express.Router();
var frase = "Encara no s'ha fet cap post"

router.get('/', (req, res) =>{
    res.json({
        'text': frase
    });
    res.end();
});

router.post('/post', (req,res) =>{
    frase = req.query.post
    res.status(201);
    res.end();
});

app.use('/.netlify/functions/api', router);

module.exports.handler = serverless(app);