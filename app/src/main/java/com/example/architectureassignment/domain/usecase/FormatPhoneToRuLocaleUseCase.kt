package com.example.architectureassignment.domain.usecase

import com.example.architectureassignment.domain.entity.PhoneFormat

/**
 * Formats a phone to RU locale in format: +7 (xxx) xxx xx xx
 */
class FormatPhoneToRuLocaleUseCase {

    operator fun invoke(phone: String, initialPhoneFormat: PhoneFormat): String {
        var result = phone

        if (initialPhoneFormat == PhoneFormat.WITH_TRAILING_DIGIT) {
            result = phone.substring(1)
        }

        val firstPart = result.substring(FIRST_PART_START_INDEX, FIRST_PART_END_INDEX)
        val secondPart = result.substring(SECOND_PART_START_INDEX, SECOND_PART_END_INDEX)
        val thirdPart = result.substring(THIRD_PART_START_INDEX, THIRD_PART_END_INDEX)
        val fourthPart = result.substring(FOURTH_PART_START_INDEX, FOURTH_PART_END_INDEX)

        return "+7 ($firstPart) $secondPart $thirdPart $fourthPart"
    }

    // Start indices are inclusive,
    // End indices are exclusive
    private companion object {
        const val FIRST_PART_START_INDEX = 0
        const val FIRST_PART_END_INDEX = 3

        const val SECOND_PART_START_INDEX = 3
        const val SECOND_PART_END_INDEX = 6

        const val THIRD_PART_START_INDEX = 6
        const val THIRD_PART_END_INDEX = 8

        const val FOURTH_PART_START_INDEX = 8
        const val FOURTH_PART_END_INDEX = 10
    }

}