const express = require('express');
const serverless = require('serverless-http');

const crypto = require('crypto');

const app = express();
const router = express.Router();

var boolean = false

function comprovarSignature(req){
    return (req.query.name == "over-18" && req.query.format == "basic" && req.query.signature == "0xC05F1284A4C04043379856AAB4B9210FAC7736B36AFD10FD245E5DD0199281E0")
}

router.get('/get', (req,res) =>{
    res.json({
        "url": "/post-credencial",
        "name": "over-18",
        "format": "basic" 
    })
    res.end()
})

router.get('/comprovar-credencial', (req,res) =>{
    if(boolean){
        res.json({
            "boolean":"true"
        })
    }
    else{
        res.json({
            "boolean":"false"
        })  
    }
    boolean = false
    res.end()
})

router.post('/post-credencial', (req,res) =>{
    if(comprovarSignature(req))
        boolean = true
    res.status(201)
    res.end()
})

app.use('/.netlify/functions/api', router);

module.exports.handler = serverless(app);