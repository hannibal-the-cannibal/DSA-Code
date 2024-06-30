const express= require('express');
const authRouter= express.Router();

const jwt = require('jsonwebtoken');
const JWT_KEY= 'dcdcexxawad'

const userModel= require('../Models/usermodel');

module.exports.signup=async function signup(req,res){
    try{
        let dataobj= req.body;
        let userdata= await userModel.create(dataobj);
        if(userdata)
        {
            res.json({
                message:"user signed up",
                data : userdata
            })
        }
        else
        {
            res.json({
                message: "error baby"
            })
        }
        
    }

    catch(err){
        res.json({
            message:err.message
           })
    }
}

module.exports.login=async function login(req,res){
    try{
        let data= req.body;
        // also we can check if data has email or its is blank
        let user= await userModel.findOne({email:data.email});
        if(user)
        {
            //bcrypt->compare 
            if(user.password==data.password)
            {
                // removing cookies and now doing json work
                // res.cookie('isLoggedIn', true);

                let uid= user._id //uid
                let jwttoken = jwt.sign({payload:uid},JWT_KEY);
                res.cookie('login', jwttoken);
                return res.json({
                    message:'Successfully logged in',
                    userDetails: data
                })
            }
            else
            {
                return res.json({
                    message:'Wrong Password '
                })
            }
        }
        else{
            return res.json({
                message:'User Not Found'
            })
        }
    }

    catch(err){
        return res.json({
            message:err.message
        })
    }
}



// roles ['admin','user','hotelowner','delivery']
module.exports.isAuthorised = function isAuthorised(roles) {
    return function (req, res, next) {
        // Check if any of the user's roles are included in the roles array
        const userRoles = roles || [];
        const isAllowed = userRoles.some(role => roles.includes(role));
        if (isAllowed) {
            next();
        } else {
            res.status(401).json({
                message: 'operation not allowed'
            });
        }
    }
}

module.exports.protectRoute = async function protectRoute(req,res,next){
    let token;
    if(req.cookies.login)
    {
        token = req.cookies.login
        let payload= jwt.verify(token, JWT_KEY);
        if(payload)
        {
            const user = await userModel.findById(payload.payload);
            req.roles= user.roles;
            req.id= user.id;
            next();
        }
        else
        {   //browser
            const client = req.get('User-Agent');
            if(client.includes("Mozilla"))
            {
                return res.redirect('/login');
            }

            //postman
            res.json({
                message:'user not verified '
            })
        }
   
    }
    else
    {
        return res.json({
            message:'you are not logged in '
        });
    }

}

//forgetpassword

module.exports.forgetPassword = async function forgetPassword(req,res){
    let {useremail}= req.body;

    try{
        const user = await userModel.findOne({email:useremail});

        if(user)
        {
                    
                //schema--> models--> doc    usercheman-->user-->get reset token method
                const resetoken= user.createResetToken();

                // http://abc.com/resetpassword/restetoken
                let resetpasswordlink= `${req.protocol}://${req.get.host}/resetpassword/${resetoken}`

                //send email to user via nodemail 

        }
        else
        {
            return res.json({
                message:'user email not found'
            })
        }


    }
    catch(err){
        return res.status(500).res.json({
            messgae: err.message
        })
    }
}

// resetpassword

module.exports.resetPassword = async function resetPassword(req,res){
try{
    const token = req.params.token
    let {password,confirmpassword}= req.body;
    const user= await userModel.findOne({resettoken:token});


    //reset password in db
    user.restPasswordHandler(password,confirmpassword)
    await user.save();

    res.json({
        message: 'Password changes successfully'
    })
}
catch(err){
    return res.json({
        message: err.message
    })
}


}

//logout

module.exports.logout=function logout(req,res){
    res.cookie('login',' ',{maxAge:1});
    res.json({
        message:' user logged out '
    })
}
