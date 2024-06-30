const reviewModel= require('../Models/reviewmodel');
const planModel= require('../Models/planmodel')


module.exports.getallreviews=async function getallreviews(req,res){
    try{
        const reviews= await reviewModel.find();
        if(reviews){
            return res.json({
                message: "saare reviews",
                data: reviews
            })
        }
        else
        {
            return res.json({
                message:'reviews nahi aa paaye'
            })
        }
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}

module.exports.top3reviews=async function top3reviews(req,res){
    try{
        const reviews= await reviewModel.find().sort({
            rating:-1
        }).limit(3);
        if(reviews){
            return res.json({
                message: "top 3 waale reviews",
                data: reviews
            })
        }
        else
        {
            return res.json({
                message:'top 3 reviews nahi aa paaye'
            })
        }
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}

module.exports.getplanreviews=async function getplanreviews(req,res){
    try{
        const pid= req.params.id
        let reviews= await reviewModel.find();
        let filterreview= reviews.filter(review => review.plan._id==pid)

        if(filterreview){
            return res.json({
                message: "id  waala reviews",
                data: filterreview
            })
        }
        else
        {
            return res.json({
                message:'id wala reviews nahi aa paaye'
            })
        }
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}

module.exports.createreview=async function createreview(req,res){
    try{
        const planid= req.params.plan;
        let plan = await planModel.findById(planid);
        let review= await reviewModel.create(req.body);

        //plan rating will be affected by this
        plan.ratingaverage= (plan.ratingaverage+ req.body.rating)/2;

        await plan.save();


        if(review){
            return res.json({
                message: "ban gaya reviews",
                data: review
            })
        }
        else
        {
            return res.json({
                message:'nai bana reviews'
            })
        }
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}

module.exports.updatereview=async function updatereview(req,res){
    try{
        const pid= req.params.id;
        let id= req.body.id;
        const review= await reviewModel.findById(id)
        if(review){

         let datatobeupdated = req.body;
         let keys=[];

         for(let key in datatobeupdated){
            if(key=='id')
            {
                continue;
            }
            keys.push(key)
         }

         for(let i=0;i<keys.length;i++)
         {
            review[keys[i]]=datatobeupdated[keys[i]];
         }

         await review.save();

            return res.json({
                message: "updated reviews",
                data: review
            })
        }
        else
        {
            return res.json({
                message:'id wala reviews nahi update ho  paaye'
            })
        }
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}

module.exports.deletereview=async function deletereview(req,res){
    try{
        const pid= req.params.id;
        let id= req.body.id;
        let review= await reviewModel.findByIdAndDelete(id);

        return res.json({
            message:'ho gaya delete',
            data: review
        })
    }
    catch(err){
        return res.json({
            message:err.message
        })
    }
}



