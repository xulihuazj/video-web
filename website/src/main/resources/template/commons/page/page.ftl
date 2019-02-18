

    <#--  前两页 … 中间三页 … 后两页  -->

    <#--当前页-->
    <#assign num = (request.pageNum)!1 >
    <#--一页条数-->
    <#assign pagesize = (request.pageSize)!10 >
    <#--总条数-->
    <#assign total = (response.bizResponse.total)!0 >

    <#--计算总页数-->
    <#if total%pagesize==0 >
        <#assign totalPage = (total/pagesize)?int >
    <#else>
        <#assign totalPage = (total/pagesize)?int+1 >
    </#if>



<#if total != 0 >
<template>
        <ul class="flex-between">

                <#--上一页-->
            <#if num-1 gt 0 >
                <li style="cursor: pointer;" onclick="goPage(${num} - 1)">上一页</li>
            <#else>
                <li style="cursor: pointer;" class="disable" >上一页</li>
            </#if>

            <#if num!=1 >
                <li style="cursor: pointer;" onclick="goPage(1)">首页</li>
            <#else>
                <li style="cursor: pointer;" class="disable" >首页</li>
            </#if>

            <#if totalPage lte 7 >

                 <#list 1..totalPage as p >
                     <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                     <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                     </#if>
                 </#list>

            <#else>

                <#if num-1 gt 3 >
                    <#assign startdot = true >
                </#if>
                <#if num+1 lt totalPage-2 >
                    <#assign enddot = true >
                </#if>

                <#--前两页-->
                <#list 1..2 as p >
                    <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                    <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                    </#if>
                </#list>

                <#if startdot??>
                 <li>…</li>
                </#if>

                <#if num gt 2 && num lt totalPage-1 >

                    <#if num=3 >
                        <#assign start=3 >
                    <#else>
                        <#assign start=num-1 >
                    </#if>
                    <#if num=totalPage-2 >
                        <#assign end=totalPage-2 >
                    <#else>
                        <#assign end=num+1 >
                    </#if>

                    <#--中间三页-->
                    <#list start..end as p >
                        <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                        <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                        </#if>
                    </#list>
                <#else>
                    <#if num==2  >
                        <#assign p=2+1 >
                        <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                        <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                        </#if>
                    </#if>
                    <#if num==totalPage-1  >
                        <#assign p=totalPage-2 >
                        <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                        <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                        </#if>
                    </#if>
                </#if>

                <#if enddot??>
                 <li>…</li>
                </#if>

                <#--后两页-->
                <#list totalPage-1..totalPage as p >
                    <#if p != num >
                        <li style="cursor: pointer;" onclick="goPage(${p})">${p}</li>
                    <#else>
                        <li class="on" style="cursor: pointer;" >${p}</li>
                    </#if>
                </#list>

            </#if>

            <#if num!=totalPage >
               <li style="cursor: pointer;" onclick="goPage(${totalPage})">末页</li>
            <#else>
               <li style="cursor: pointer;" class="disable" >末页</li>
            </#if>

            <#if num+1 <= totalPage >
                <li style="cursor: pointer;" onclick="goPage(${num+1})">下一页</li>
            <#else>
                <li style="cursor: pointer;" class="disable" >下一页</li>
            </#if>

        </ul>
    <script>
        function goPage(p) {
            $(document).trigger("p-gopage.page", p);
        }
    </script>
</template>
</#if>
