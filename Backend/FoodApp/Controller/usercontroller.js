const userModel= require('../Models/usermodel');

 module.exports.getUser=async function getUser(req,res){
    let id= req.id;
    let user= await userModel.findById(id);
    if(user)
    {
        return res.json(user);
    }
    else
    {
        return res.json({
            message: 'user not found'
        });
    }
};

// module.exports.postUser=function postUser(req,res){
//     console.log(req.body);
//     users=req.body;
//     res.json({
//         message:'Data success',
//         user: req.body
//     });
// }

module.exports.patchUser=async function patchUser(req,res){
    try {
        let id= req.params.id;
        let user= await userModel.findById(id);
        let datatobeupdated= req.body;

    if(user)
    {
        const keys=[];
        for(let key in datatobeupdated){
            keys.push(key);
        }

        for(let i=0;i<keys.length;i++)
        {
            user[keys[i]]=datatobeupdated[keys[i]];
        }

        const updatedata= await user.save();
        res.json({
            message: 'data updated successfully',
            data:updatedata
        });
    }
    else
    {
        res.json({
            message: 'user not found'
        });
    }
    } catch (error) {
        res.json({
            message: error.message
        });
    }
}


module.exports.deleteUser=async function deleteUser(req,res){
    // users={};
    try{
        let id= req.params.id;
        let user= await userModel.findByIdAndDelete(id);
    if(!user)
    {
        res.json({
            message:'user not found'
        })
    }

        res.json({
            message: 'Data deleted',
            data:user
        });
    }
    catch(err){
        res.json({
            message: err.message
            
        });
    }
}


module.exports.getallUsers=async function getallUsers(req,res){
    let users= await userModel.find();
    if(users)
    {
        res.json({
            message:'users reterived',
            data: users
        });
    }

}

module.exports.setcookies=function setcookies(req,res){
    // res.setHeader('Set-Cookie','isLoggedIn=true');
    res.cookie('isLoggedIn',false,{maxAge:1000*60*60*24, secure:true, httpOnly:true});
    res.send('cookies are set');
}

module.exports.getcookies=function getcookies(req,res){
    let cookies= req.cookies;
    console.log(cookies);
    res.send('cookies receive')
}
