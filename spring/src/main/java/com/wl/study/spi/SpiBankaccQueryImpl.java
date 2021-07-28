package com.wl.study.spi;

import com.jd.jr.epp.bankacc.api.facade.BankaccQueryFacade;
import com.jd.jr.epp.bankacc.api.request.*;
import com.jd.jr.epp.bankacc.api.request.query.*;
import com.jd.jr.epp.bankacc.api.response.*;
import com.jd.jr.epp.bankacc.api.response.query.*;
import com.jd.jr.eprd.epp.common.response.Response;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 得实现之前的接口，不然会提示：Provider com.wl.study.spi.SpiBankaccQueryImpl not a subtype
 *
 * 使用spi的步骤
 * １，引入原接口jar
 * ２，在resources目录下新建META-INF.services目录，接着在此目录下新建文件，
 * 　　文件名原接口全限定名，文件内容为自定义的实现原接口的类
 * ３，使用自定义的类，实现原接口，重写需要覆盖的类
 */
public class SpiBankaccQueryImpl implements BankaccQueryFacade{

    @Override
    public Response<GetBankaccByCustomerIdResponse> getBankaccByCustomerId(GetBankaccByCustomerIdRequest request){
        System.out.println("hello");

        return null;
    }

    public static void main(String[] args) {
        ServiceLoader<BankaccQueryFacade> loader = ServiceLoader.load(BankaccQueryFacade.class);
        Iterator<BankaccQueryFacade> iterator = loader.iterator();
        GetBankaccByCustomerIdRequest request = new GetBankaccByCustomerIdRequest();
        while (iterator.hasNext()){
            BankaccQueryFacade queryFacade = iterator.next();
            //这个会调用本类中的方法，而不是其它的实现
            queryFacade.getBankaccByCustomerId(request);
        }
    }

    @Override
    public Response<GetBankaccByCompanyUserIdAndMerchantNoResponse> getBankaccByCompanyUserIdAndMerchantNo(GetBankaccByCompanyUserIdAndMerchantNoRequest getBankaccByCompanyUserIdAndMerchantNoRequest) {
        return null;
    }

    @Override
    public Response<GetBankaccByJdpinResponse> getBankaccByJdpin(GetBankaccByJdpinRequest getBankaccByJdpinRequest) {
        return null;
    }

    @Override
    public Response<GetBankCardByBindIdResponse> getBankCardByBindId(GetBankCardByBindIdRequest getBankCardByBindIdRequest) {
        return null;
    }

    @Override
    public Response<GetBankaccByMerchantCodeResponse> getBankaccByMerchantCode(GetBankaccByMerchantCodeRequest getBankaccByMerchantCodeRequest) {
        return null;
    }

    @Override
    public Response<GetBankaccByAccountNoResponse> getBankaccByAccountNo(GetBankaccByAccountNoRequest getBankaccByAccountNoRequest) {
        return null;
    }

    @Override
    public Response<GetChannelBankSupportResponse> getChannelBankSupport(GetChannelBankSupportRequest getChannelBankSupportRequest) {
        return null;
    }

    @Override
    public Response<GetChannelBankSupportResponse> getSupportBankList(SupportBankRequest supportBankRequest) {
        return null;
    }

    @Override
    public Response<BankaccCustomer> queryBankAccountUserInfo(BankaccountQueryRequest bankaccountQueryRequest) {
        return null;
    }

    @Override
    public Response<BankaccQueryAccountPageResponse> getBankaccCustomerByChannel(BankaccQueryAccountPageRequest bankaccQueryAccountPageRequest) {
        return null;
    }

    @Override
    public Response<GetBankaccCompanyByAccountNoResponse> getBankaccCustomerByBankAccountNo(GetBankaccCompanyByAccountNoRequest getBankaccCompanyByAccountNoRequest) {
        return null;
    }

    @Override
    public Response<QueryUpdateRecordResponse> queryUpdateRecordInfo(QueryUpdateRecordRequest queryUpdateRecordRequest) {
        return null;
    }

    @Override
    public Response<List<BankaccCustomer>> queryPayForAcccountInfo(QueryPayForAcccountInfoRequest queryPayForAcccountInfoRequest) {
        return null;
    }

    @Override
    public Response<BankaccCustomer> queryAccountInfoByAttachNo(QueryAccountInfoRequest queryAccountInfoRequest) {
        return null;
    }

    @Override
    public Response<FileExistResponse> checkFlieExist(FileExistRequest fileExistRequest) {
        return null;
    }

    @Override
    public Response<EppRasApplyDataResponse> queryEppRasApplyDataByUserId(EppRasApplyDataRequest eppRasApplyDataRequest) {
        return null;
    }

    @Override
    public Response<RetryAccountInfoResponse> queryToRetryAccountInfo(QueryToRetryOpenAccountRequest queryToRetryOpenAccountRequest) {
        return null;
    }

    @Override
    public Response<RetryBindInfoResponse> queryToRetryBindInfo(QueryToRetryBindCardRequest queryToRetryBindCardRequest) {
        return null;
    }

    @Override
    public Response<RetryVerifyResponse> queryToRetryVerifyInfo(QueryToRetryBindCardRequest queryToRetryBindCardRequest) {
        return null;
    }

    @Override
    public Response<GetBankaccByCustomerIdResponse> getBankaccByAccId(GetBankaccByAccIdRequest getBankaccByAccIdRequest) {
        return null;
    }

    @Override
    public Response<BankaccAccountStatusResponse> queryBankAccountStatus(QueryAccountStatusRequest queryAccountStatusRequest) {
        return null;
    }
}
