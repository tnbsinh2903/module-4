class AppPage {
    static renderFooter(customer) {
        return `
   <div class="footer">
                             <div class="text-center">
                               <a class="btn btn-outline-secondary edit" data-id="${customer.id}"   title="Edit" data-toggle="tooltip" data-toggle="modal" data-target="#modelUpdate" >
                                  <i class="fas fa-edit" aria-hidden="true"></i>
                               </a>
                            </div>
                            <div class="text-center">
                                <a class="btn btn-outline-success deposit"  data-id="${customer.id}"  title="Deposit" data-toggle="tooltip" data-toggle="modal" data-target="#modelUpdate"  data-bs-original-title="Deposit">
                                  <i class="fas fa-plus" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="text-center">
                                <a class="btn btn-outline-warning withdraw"  data-id="${customer.id}"  title="Withdraw" data-toggle="tooltip"    title="Withdraw">
                                  <i class="fas fa-minus" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="text-center">
                                <a class="btn btn-outline-primary transfer"  data-id="${customer.id}" title="Transfer" data-toggle="tooltip"   title="Transfer">
                                  <i class="fas fa-exchange-alt" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="text-center">
                                <a class="btn btn-outline-danger suspended"  data-id="${customer.id}" title="Suspended" data-toggle="tooltip"   title="Suspended">
                                          <!--   onclick="myfunction()"-->
                                   <i class="fas fa-ban" aria-hidden="true"></i>
                                </a>
                            </div>
   </div>
   `;
    }

}