<%@ include file="../common/IncludeTop.jsp"%>
<style>
   .announce{
      display: flex;
      font-size: 16px;
      font-family: Poppins, sans-serif;
      overflow: hidden !important;
      justify-content:space-around;
      align-items: center;
   }
   .headLine {
      font-size: 3em;
      color: #000; /* Text color */
   }
   .paraLine {
      font-weight: 600;
      letter-spacing: 2px;
      width: 40%;
   }
</style>
<div class="announce">
   <section style="text-align: left">
      <section class="headLine">
         <p>
            OOPS! SOMETHING
         </p>
         <p>
            WENT WRONG.
         </p>
      </section>
      <section class="paraLine">
         this is the error page.
      </section>
   </section>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>