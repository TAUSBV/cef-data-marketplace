import TransferwiseApiClient from "@/services/TransferwiseApiClient";
import { AxiosResponse } from "axios";
import { TransferWiseBank, TransferWiseBranch, TransferWiseResponseEnvelope } from "@/models/models";

export default {
  getBanks(countryCode: string): Promise<AxiosResponse<TransferWiseResponseEnvelope<TransferWiseBank>>> {
    return TransferwiseApiClient.get(`/banks?country=${countryCode}`);
  },

  getBankBranches(
    countryCode: string,
    bankCode: string
  ): Promise<AxiosResponse<TransferWiseResponseEnvelope<TransferWiseBranch>>> {
    return TransferwiseApiClient.get(`/bank-branches?country=${countryCode}&bankCode=${bankCode}`);
  },
  validateIban(iban: string): Promise<AxiosResponse<{ validate: string }>> {
    return TransferwiseApiClient.get(`/validators/iban?iban=${iban}`);
  },

  validateIfsc(ifsc: string): Promise<AxiosResponse<{ validate: string }>> {
    return TransferwiseApiClient.get(`/validators/ifsc-code?ifscCode=${ifsc}`);
  },

  validateIndianAccountNumber(accountNumber: string): Promise<AxiosResponse<{ validate: string }>> {
    return TransferwiseApiClient.get(`/validators/indian-account-number?accountNumber=${accountNumber}`);
  }
};
