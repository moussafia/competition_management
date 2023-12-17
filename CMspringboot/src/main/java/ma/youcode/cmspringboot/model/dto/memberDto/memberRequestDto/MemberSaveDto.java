package ma.youcode.cmspringboot.model.dto.memberDto.memberRequestDto;

import ma.youcode.cmspringboot.model.domain.IdentityDocumentType;
import ma.youcode.cmspringboot.model.domain.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record MemberSaveDto(
        @NotBlank @NotNull
        String firstName,
        @NotBlank @NotNull

        String lastName,
        @NotBlank @NotNull

        IdentityDocumentType identityDocumentType,
        @NotBlank @NotNull

        String identityNumber,
        @NotBlank @NotNull

        String nationality
) {
    public static Member toMember(MemberSaveDto memberSaveDto){
        return  new Member().builder()
                .name(memberSaveDto.firstName)
                .familyName(memberSaveDto.lastName)
                .identityNumber(memberSaveDto.identityNumber)
                .identityDocumentType(memberSaveDto.identityDocumentType)
                .nationality(memberSaveDto.nationality)
                .build();
    }
}
