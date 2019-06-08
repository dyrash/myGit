export interface Contract {
    contractNo: string;
    estimateNo: string;
    contractType?: string;
    contractDate?: string;
    contractRequester?: string;
    unitOfDeliveryResult?: string;
    deliveryResultStatus?: string;
    slipRegistStatus?: string;
    description?: string;
    customerCode?: string;
    status?: string;
    contractDetailList?:any[]
}
