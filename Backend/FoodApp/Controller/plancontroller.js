const planModel= require('../Models/planmodel')

module.exports.getallplans=async function getallplans(req,res){
    try{
        let plans= await planModel.find();
        if(plans)
        {
            return res.json({
                message:'All Plans found',
                data: plans
            })
        }
        else
        {
            return res.json({
                message:'All Plans not found',
            })
        }
    }
    catch(err){
        return res.status(500).json({
            message: err.message
        })
    }
}

module.exports.getplan=async function getplan(req,res){
    try{
        let id= req.params.id;
        let plan= await planModel.findById(id);
        console.log(plan)
        if(plan)
        {
            return res.json({
                message:' Plan found',
                data: plan
            })
        }
        else
        {
            return res.json({
                message:' Plan not found',
            })
        }
    }
    catch(err){
        return res.status(500).json({
            message: err.message
        })
    }
}

module.exports.createplan= async function createplan(req,res){
   try{
        let plandata= req.body;
        let createdplan = await planModel.create(plandata);

        return res.json({
            message:'plan created successfully',
            data: createdplan
        })
   }
   catch(err){
        return res.json({
            message:err.message
        })
   }

}

module.exports.updateplan= async function updateplan(req,res){
    try{
        let id= req.params.id;

         let datatobeupdated = req.body;
         let keys=[];

         for(let key in datatobeupdated){
            keys.push(key)
         }
         let plan =await planModel.findById(id);

         for(let i=0;i<keys.length;i++)
         {
            plan[keys[i]]=datatobeupdated[keys[i]];
         }

         await plan.save();
 
         return res.json({
             message:'plan updated successfully',
             data: plan
         })
    }
    catch(err){
         return res.json({
             message:err.message
         })
    }
 
 }

 module.exports.deleteplan= async function deleteplan(req,res){
    try{
         let id= req.params.id
         let deletedplan = await planModel.findByIdAndDelete(id);
 
         return res.json({
             message:'plan deleted successfully',
             data: deletedplan
         })
    }
    catch(err){
         return res.json({
             message:err.message
         })
    }
 
 }


 module.exports.top3plans=async function top3plans(req,res){
    try{
        const plans3= await planModel.find().sort({
            ratingaverage:-1
        }).limit(3);

        return res.json({
            message: 'top 3 plans aa gaye',
            data: plans3
        })


    }
    catch(err)
    {
        res.status(500).json({
            message: err.message
        })
    }
 }





 


