let video= document.querySelector("video");
let recordbtncont = document.querySelector(".record-btn-cont");
let recordbtn = document.querySelector(".record-btn");
let capturebtncont = document.querySelector(".capture-btn-cont");
let capturebtn = document.querySelector(".capture-btn");

let timerid;
let counter=0; // total seconds

let filtercolor="transparent";

let recordflagonoff=false;
let recorder;
let chunks = [];// media data

let constraints={
    video:true,
    audio:true
}

// navigator is a global object that keeps browser info
navigator.mediaDevices.getUserMedia(constraints)
.then((stream)=>{
    video.srcObject= stream;

    //record 
    recorder= new MediaRecorder(stream);

    recorder.addEventListener("start", (e)=>{
        chunks=[];
    })

    recorder.addEventListener("dataavailable", (e)=>{
        chunks.push(e.data);
    })

    recorder.addEventListener("stop",(e)=>{
        //conversion of media chunks data to video
        let blob = new Blob(chunks, { type : 'video/mp4'});
        let videoId= shortid();

        if(db)
        {
            let dbtransaction = db.transaction("Videos","readwrite");
            let videostore= dbtransaction.objectStore("Videos");
            let videoentry={
                id: `video-${videoId}`,
                blobData: blob
            }
            videostore.add(videoentry);
        }



        // let videourl= URL.createObjectURL(blob);
        // let a= document.createElement("a");
        // a.href= videourl;
        // a.download= "stream.mp4";
        // a.click();
    })
})

recordbtncont.addEventListener("click", (e)=>{
    if(!recorder) return ;

    recordflagonoff=!recordflagonoff;
    if(recordflagonoff==true)
    {
        //start
        recorder.start();
        recordbtn.classList.add("scale-record");
        starttimer();
    }
    else
    {
        //stop
        recorder.stop();
        recordbtn.classList.remove("scale-record");
        stoptimer();
    }
})



capturebtncont.addEventListener("click", (e)=>{

    capturebtn.classList.add("scale-capture");
    //video is collection of frames , so image is just one frame
    let canvas= document.createElement("canvas");
    canvas.width=video.videoWidth;
    canvas.height=video.videoHeight;

    let tool= canvas.getContext("2d");
    tool.drawImage(video,0,0,canvas.width, canvas.height);

    // add filters
    tool.fillStyle=filtercolor;
    tool.fillRect(0,0,canvas.width, canvas.height);

    let imageurl= canvas.toDataURL();
    let imageId= shortid();

        if(db)
        {
            let dbtransaction = db.transaction("Images","readwrite");
            let imagestore= dbtransaction.objectStore("Images");
            let imageentry={
                id: `img-${imageId}`,
                blobData: imageurl
            }
            imagestore.add(imageentry);
        }

        setTimeout(()=>{
            capturebtn.classList.remove("scale-capture");
        },500)


    
    // let a= document.createElement("a");
    // a.href= imageurl;
    // a.download= "image.jpg";
    // a.click();
})



let timer= document.querySelector(".timer")
function starttimer(){
    timer.style.display= "block";
    function displaytimer(){
        let totalseconds= counter;
        let hours=Number.parseInt(totalseconds/3600);
        totalseconds=totalseconds%3600;
        let minutes= Number.parseInt(totalseconds/60);
        totalseconds=totalseconds%60;
        let seconds= totalseconds;

        hours=(hours<10)? `0${hours}`:hours;
        minutes=(minutes<10)? `0${minutes}`:minutes;
        seconds=(seconds<10)? `0${seconds}`:seconds;


        timer.innerText=`${hours}:${minutes}:${seconds}`;
        counter++;
    }
    timerid= setInterval(displaytimer,1000);
}

function stoptimer(){
    timer.style.display= "none";
    clearInterval(timerid);
    timer.innerText="00:00:00";
}

let filterlayer= document.querySelector(".filter-layer");
let allfilters= document.querySelectorAll(".filter");
allfilters.forEach((filterele)=>{
    filterele.addEventListener("click", (e)=>{
       filtercolor= getComputedStyle(filterele).getPropertyValue("background-color");
       filterlayer.style.backgroundColor= filtercolor;
    })
})



