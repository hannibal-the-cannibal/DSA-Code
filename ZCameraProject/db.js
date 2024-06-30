let db;
let openrequest= indexedDB.open("myDB");
openrequest.addEventListener("success",(e)=>{
    console.log("DB succes");
    db= openrequest.result;
})
openrequest.addEventListener("error",(e)=>{
    console.log("DB error");
})
openrequest.addEventListener("upgradeneeded",(e)=>{
    console.log("DB uprageded and also fro intial DB creation ");
    //intially db created 
    db= openrequest.result;
    // objectStore only created in db upgraded 

    db.createObjectStore("Videos",{keyPath: "id"});
    db.createObjectStore("Images" , {keyPath:"id"});
})

