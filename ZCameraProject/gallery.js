setTimeout(() => {
    if (db) {
        // Retrieval of video data from db
        let videodbtransaction = db.transaction("Videos", "readonly");
        let videoStore = videodbtransaction.objectStore("Videos");
        let videorequest = videoStore.getAll();
        videorequest.onsuccess = (e) => {
            let gallerycont = document.querySelector(".gallery-cont");

            let videoresult = videorequest.result;
            videoresult.forEach((videoobj) => {
                let mediaele = document.createElement("div");
                mediaele.setAttribute("class", "media-cont");
                mediaele.setAttribute("id", videoobj.id);

                let url = URL.createObjectURL(videoobj.blobData);

                mediaele.innerHTML = `
                    <div class="media">
                        <video autoplay loop src="${url}"></video>
                    </div>
                    <div class="download action-btn">Download</div>
                    <div class="delete action-btn">Delete</div>
                `;

                // Adding child to our gallery
                gallerycont.appendChild(mediaele);
            });

            // Delete and download functionality for videos
            let deleteButtons = document.querySelectorAll(".delete");
            let downloadButtons = document.querySelectorAll(".download");

            deleteButtons.forEach((deleteBtn) => {
                deleteBtn.addEventListener("click", deletelistner);
            });

            downloadButtons.forEach((downloadBtn) => {
                downloadBtn.addEventListener("click", downloadlistner);
            });
        };
    }

    // Image retrieval
    let imagedbtransaction = db.transaction("Images", "readonly");
    let imageStore = imagedbtransaction.objectStore("Images");
    let imagerequest = imageStore.getAll();
    imagerequest.onsuccess = (e) => {
        let gallerycont = document.querySelector(".gallery-cont");

        let imageresult = imagerequest.result;
        imageresult.forEach((imageobj) => {
            let mediaele = document.createElement("div");
            mediaele.setAttribute("class", "media-cont");
            mediaele.setAttribute("id", imageobj.id);

            let url = imageobj.blobData;

            mediaele.innerHTML = `
                <div class="media">
                    <img src="${url}"/>
                </div>
                <div class="download action-btn">Download</div>
                <div class="delete action-btn">Delete</div>
            `;

            gallerycont.appendChild(mediaele);
        });

        // Delete and download functionality for images
        let deleteButtons = document.querySelectorAll(".delete");
        let downloadButtons = document.querySelectorAll(".download");

        deleteButtons.forEach((deleteBtn) => {
            deleteBtn.addEventListener("click", deletelistner);
        });

        downloadButtons.forEach((downloadBtn) => {
            downloadBtn.addEventListener("click", downloadlistner);
        });
    };
}, 100);

// UI remove and DB remove when delete
function deletelistner(e) {
    let id = e.target.parentElement.getAttribute("id");
    if (id.slice(0, 3) === "vid") {
        let videodbtransaction = db.transaction("Videos", "readwrite");
        let videoStore = videodbtransaction.objectStore("Videos");
        videoStore.delete(id);
    } else {
        let imagedbtransaction = db.transaction("Images", "readwrite");
        let imageStore = imagedbtransaction.objectStore("Images");
        imageStore.delete(id);
    }

    // UI removal
    e.target.parentElement.remove();
}

function downloadlistner(e) {
    let id = e.target.parentElement.getAttribute("id");
    if (id.slice(0, 3) === "vid")
    {
        let videodbtransaction = db.transaction("Videos", "readwrite");
        let videoStore = videodbtransaction.objectStore("Videos");
        let videorequest= videoStore.get(id);
        videorequest.onsuccess=(e)=>{
            let videoresult= videorequest.result;

            let videourl= URL.createObjectURL(videoresult.blobData);
            let a= document.createElement("a");
            a.href= videourl;
            a.download= "stream.mp4";
            a.click();
        }
    }
    else
    {
        let imagedbtransaction = db.transaction("Images", "readwrite");
        let imageStore = imagedbtransaction.objectStore("Images");
        let imagerequest= imageStore.get(id);
        imagerequest.onsuccess=(e)=>{
            let imageresult= imagerequest.result;

            let imageurl= imageresult.blobData
            let a= document.createElement("a");
            a.href= imageurl;
            a.download= "image.jpg";
            a.click();
        }
    }
}