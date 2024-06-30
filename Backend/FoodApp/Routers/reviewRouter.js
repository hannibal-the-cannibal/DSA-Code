const express= require('express');
const reviewRouter= express.Router();

const{protectRoute, isAuthorised}= require('../Controller/authcontroller')
const {getallreviews,top3reviews,getplanreviews ,createreview,updatereview, deletereview}= require('../Controller/reviewcontroller')


reviewRouter
.route('/all')
.get(getallreviews);

reviewRouter
.route('/top3')
.get(top3reviews);

reviewRouter.
route('/:id')
.get(getplanreviews);


reviewRouter.use(protectRoute)
reviewRouter
.route('/crud/:plan')
.post(createreview)
.patch(updatereview)
.delete(deletereview)






module.exports= reviewRouter;


