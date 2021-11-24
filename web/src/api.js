const express = require('express');
const serverless = require('serverless-http');

const app = express();
const router = express.Router();
var frase = "Encara no s'ha fet cap post"
var boolean = false

router.get('/get', (req,res) =>{
    res.json({
        "url": "/post-credencial",
        "send": "boolean" 
    })
    res.end()
})

router.get('/comprovar-credencial', (req,res) =>{
    if (boolean == true){
        res.json({
            "boolean": "true"
        })
    }
    else{
        res.json({
            "boolean": "false"
        })
    }
    boolean = false
    res.end()
})

router.post('/post-credencial', (req,res) =>{
    if(req.query.boolean == "12345")
        boolean = true
    res.status(201)
    res.end()
})






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