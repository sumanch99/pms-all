package com.cognizant.pms.processpension.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pms.processpension.apiResponse.ApiResponsePd;
import com.cognizant.pms.processpension.exception.ProcessPensionException;
import com.cognizant.pms.processpension.feignClient.AuthInterface;
import com.cognizant.pms.processpension.feignClient.GpdInterface;
import com.cognizant.pms.processpension.model.Bank;
import com.cognizant.pms.processpension.model.PensionDetail;
import com.cognizant.pms.processpension.model.Pensioner;
import com.cognizant.pms.processpension.model.PensionerDetails;
import com.cognizant.pms.processpension.repository.PensionDetailRepository;
import com.cognizant.pms.processpension.repository.PensionerBankRepository;
import com.cognizant.pms.processpension.repository.PensionerRepository;
import com.cognizant.pms.processpension.util.CalculatePensionUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessPensionService {
	
    @Autowired
    private GpdInterface gpdClient;
    
    @Autowired
    private AuthInterface authClient;
    
    @Autowired
    private PensionDetailRepository pensionDetailRepository;

	@Autowired
	private PensionerRepository pensionerRepository;
	
	@Autowired
	private PensionerBankRepository bankRepository;
	
	public boolean validateAuth(String authToken) {
		log.info("Entered validateAuth()");
		try {
			log.info("Token Validated");
			log.info("Exiting validateAuth()");
			return (Boolean)authClient.validateAuthToken(authToken).getData();
		}catch(Exception e) {
			log.error(e.getMessage());
			return false;
		}
		
		
	}
	
    public PensionDetail processPension(String authToken, String aadhar) {
    	log.info("Entered processPension() for aadharNo:"+aadhar);
    	log.info("Calling feignClient getPensionerDetails");
        ApiResponsePd pensionerDetails =  gpdClient.getPensionerDetails(authToken, aadhar);
        log.info("Called feignClient getPensionerDetails, response:"+pensionerDetails);
        PensionerDetails pd = pensionerDetails.getData();
        if(pd == null){
        	log.error("Aadhar no. is incorrect. Input a valid aadhar no.");
            throw new ProcessPensionException("Aadhar no. is incorrect. Input a valid aadhar no.");
        }
        log.info("Fetching pensionDetail from repository if available");
        Optional<PensionDetail> pensionDetails = pensionDetailRepository.findByAadharNo(aadhar);
        log.info("Data fetched:"+pensionDetails);
        if(pensionDetails.isPresent()) {
        	log.info("Pension already processed for the pensioner");
            throw new ProcessPensionException("Pension already processed for the pensioner");
        }
        log.info("Calling calculatePension");
        Double pensionAmount = CalculatePensionUtil.calculatePension(pd.getPensioner().getPensionType(),
        		pd.getBank().getBankName(), pd.getBank().getBankType(),
        		pd.getPensioner().getSalary(), pd.getPensioner().getAllowance());
        log.info("Pension Amount calculated as: Rs."+pensionAmount);
		PensionDetail pensionDetail;
		try {
			pensionDetail = new PensionDetail(aadhar, pensionAmount,
					CalculatePensionUtil.getBankServiceChargeFromCSV(pd.getBank().getBankName()));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ProcessPensionException(e.getMessage());
		}
		savePensioner(pd.getPensioner());
		savePensionerBank(pd.getBank());
		return savePensionDetail(pensionDetail);
        
    }

    public PensionDetail getPensionerDetailsByAadhar(String aadharNo) {
    	log.info("Entered processPension() for aadharNo:"+aadharNo);
    	log.info("Calling findByAadharNo to fetch PensionDetail from repository if available");
        Optional<PensionDetail> byAadharNo = pensionDetailRepository.findByAadharNo(aadharNo);
        if(byAadharNo.isPresent()){
        	log.info("PensionDetail byAadharNo found in repository");
            return byAadharNo.get();
        }
        else{
        	log.info("PensionDetail byAadharNo not found in repository");
            throw new ProcessPensionException("Aadhar no. not found");
        }
    }
    
    private Pensioner savePensioner(Pensioner pensioner) {
    	log.info("Saving Pensioner in repository");
		return pensionerRepository.saveAndFlush(pensioner);
	}

	private PensionDetail savePensionDetail(PensionDetail pensionDetail) {
		log.info("Saving PensionDetail in repository");
		return pensionDetailRepository.saveAndFlush(pensionDetail);
	}
	
	private Bank savePensionerBank(Bank bank) {
		log.info("Saving Bank in repository");
		return bankRepository.saveAndFlush(bank);
	}
}
