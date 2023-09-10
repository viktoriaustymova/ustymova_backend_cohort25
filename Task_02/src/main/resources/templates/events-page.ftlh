<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Events</title>
</head>
<body>
<div>

</div>
     <table>
         <tr>
             <th>Название события</th>
             <th>Описание события</th>
         </tr>
         <#list eventsList as event>
             <tr>
                 <td>${event.name}</td>
                 <td>${event.desc}</td>
             </tr>
         </#list>
     </table>
</body>
</html>