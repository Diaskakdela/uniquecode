package kz.geekprtnrs.queueinterviewtask.service;

import kz.geekprtnrs.queueinterviewtask.entity.UniqueCode;
import kz.geekprtnrs.queueinterviewtask.repository.UniqueCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniqueCodeService {
    final UniqueCodeRepository uniqueCodeRepository;

    public UniqueCodeService(UniqueCodeRepository uniqueCodeRepository) {
        this.uniqueCodeRepository = uniqueCodeRepository;
    }

    public Optional<UniqueCode> getNewCode(){
        Optional<UniqueCode> uniqueCode = uniqueCodeRepository.findAll().stream().findFirst();
        if(uniqueCode.isPresent()){
            UniqueCode presentUniqueCode = uniqueCode.get();
            String currentCode = presentUniqueCode.getCode();

            presentUniqueCode.setCode(getCode(currentCode));

            return Optional.of(uniqueCodeRepository.save(presentUniqueCode));
        }
        return Optional.empty();
    }


    private String getCode(String str){
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();

        int maxNine = str.length()/2;
        int currentNinesCount = 0;
        int currentZCount = 0;

        for(int i = 0; i< str.length(); i++){
            if(Character.isDigit(chars[i]) && chars[i]=='9'){
                currentNinesCount++;
            }
        }
        for(int i = 0; i< str.length(); i++){
            if(!Character.isDigit(chars[i]) && chars[i]=='z'){
                currentZCount++;
            }
        }

        if(currentNinesCount==maxNine){
            if(currentZCount==(str.length()/2)){
                stringBuilder.append("a0".repeat((str.length()/2)+1));
                return stringBuilder.toString();
            }
            for(int i = 0; i< str.length();i++){
                if(Character.isDigit(chars[i])){
                    chars[i]='0';
                }
            }
            for(int i = chars.length-1; i>=0; i--){
                if(!Character.isDigit(chars[i])){
                    if(chars[i]!='z'){
                        ++chars[i];
                        return stringBuilder.append(chars).toString();
                    }
                    else{
                        chars[i]='a';
                    }
                }
            }
        }

        for(int i = chars.length-1; i>=0; i--){
            if(Character.isDigit(chars[i])){
                if(chars[i]!='9'){
                    chars[i]++;
                    return stringBuilder.append(chars).toString();
                }
                else{
                    chars[i]='0';
                }
            }

        }
        return null;
    }
}
