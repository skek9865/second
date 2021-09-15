package com.with.second.service;


import com.with.second.dto.BucketDto;
import com.with.second.entity.BucketEntity;
import com.with.second.repository.BucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;

    @Override
    public void register(BucketDto dto) {

        log.info("bucketRegister...");

        BucketEntity bucketEntity = dtoToEntity(dto);

        log.info("bucketEntity : " + bucketEntity);


        bucketRepository.save(bucketEntity);

    }

    @Transactional
    @Override
    public List<BucketDto> getList(String id) {

        log.info("bucketGetList...");

        List<Object[]> bucketList = bucketRepository.getList(id);

        List<BucketDto> bucketDtos = new ArrayList<>();

        for(Object[] entity : bucketList){

            BucketDto dto = entityToDto((BucketEntity) entity[0], (String) entity[1]);

            bucketDtos.add(dto);
        }

        log.info("bucketDtos : " + bucketDtos);

        return bucketDtos;
    }


    @Transactional
    @Override
    public void remove(Long sno) {

        bucketRepository.deleteById(sno);

    }
}
