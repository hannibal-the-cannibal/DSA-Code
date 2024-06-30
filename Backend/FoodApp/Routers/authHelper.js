const jwt = require('jsonwebtoken');
const JWT_KEY= 'dcdcexxawad'


// let flag=false; /// user logged in 

// function protectRoute(req,res,next){
//     if(req.cookies.login)
//     {
//         let isverified= jwt.verify(req.cookies.login, JWT_KEY);
//         if(isverified)
//         {
//             next();
//         }
//         else
//         {
//             return res.json({
//                 message:'you are not verified  '
//             });
//         }
       
//     }
//     else
//     {
//         return res.json({
//             message:'you are not logged in '
//         });
//     }

// }

// module.exports=protectRoute;