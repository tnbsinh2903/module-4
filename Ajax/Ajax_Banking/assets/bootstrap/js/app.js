class App {



    static showSuspendedConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to suspend the selected customer ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please suspend this client !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ');
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    // static formatNumberSpace(x) {
    //     return x.toString.replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    // }

    // static removeFormatNumberSpace(x) {
    //     return x.toString().replace(" ", "");
    // }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}

class Customer {
    constructor(id, fullName, email, phone, address, balance, deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.deleted = deleted;
    }
}

class Sender {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Recipient {
    constructor(id, fullName, email, phone, address, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }
}

class Deposit {
    constructor(customerId, fullName, balance, transactionAmount) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
    }
}

class Withdraw {
    constructor(customerId, nameWh, balance, transactionAmount) {
        this.customerId = customerId;
        this.nameWh = nameWh;
        this.balance = balance;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(transferId, senderId, senderName, email, recipientId, recipientName, balance, transferAmount, fees, feesAmount, transactionAmount) {
        this.transferId = transferId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.email = email;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.balance = balance;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
    }
}