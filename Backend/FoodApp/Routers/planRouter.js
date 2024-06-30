const express= require('express');
const planRouter= express.Router(); 
const{protectRoute, isAuthorised}= require('../Controller/authcontroller')

const{getallplans, top3plans, getplan, createplan, updateplan, deleteplan}= require('../Controller/plancontroller')



//all plans
planRouter.route('/allplans')
.get(getallplans)


//own plan ->should be logged in 
planRouter.use(protectRoute)
planRouter.route('/plans/:id')
.get(getplan)


//only admin and hotel owner can changes these 
planRouter.use(isAuthorised(['admin','hotelowner']))
planRouter
.route('/crudplan')
.post(createplan)


planRouter.use(isAuthorised(['admin','hotelowner']))
planRouter
.route('/crudplan/:id')
.patch(updateplan)
.delete(deleteplan)




//top3 plans for UI
planRouter.route('/top3plans')
.get(top3plans)

module.exports=planRouter

