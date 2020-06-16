function openHistory(){
    var menu=document.getElementsByClassName("history")[0].style.display="block";
    var title=document.getElementsByClassName("title")[0];
    title.setAttribute('onclick','closeHistory()');
}
function closeHistory(){
    var menu=document.getElementsByClassName("history")[0].style.display="none";
    var title=document.getElementsByClassName("title")[0];
    title.setAttribute('onclick','openHistory()')
}