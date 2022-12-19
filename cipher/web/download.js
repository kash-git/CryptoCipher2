let downloadEncrypted=function(){
    let xhr=new XMLHttpRequest();
    //alert("hello");
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if((xhr.status>=200&&xhr.status<300)||xhr.status===304){
                //alert("hello");
                xhr.responseText;
                alert("file download")
            }else{
                alert("Request Unsuccessful");
            }
        }
    };
    //alert(a)
    xhr.open("get","download",true);
    xhr.send(null);
};
let downloadDecrypted=function(){
    let xhr=new XMLHttpRequest();
    //alert("hello");
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if((xhr.status>=200&&xhr.status<300)||xhr.status===304){
                //alert("hello");
                xhr.responseText;
                alert("file download")
            }else{
                alert("Request Unsuccessful");
            }
        }
    };
    //alert(a)
    xhr.open("get","downloadd",true);
    xhr.send(null);
};


