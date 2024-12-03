package adventcode.day03

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Day03Test: StringSpec ({
    val input = this::class.java.classLoader.getResource("./day-03-input.txt")!!.readText()
    val sampleInput1 = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"""
    val sampleInput2 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val sampleInput3 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+don't()mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val sampleInput4 = """xmul(2,4)&do()do()mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""
    val sampleInput5 = """xmul(2,4)&do()mul(4,6);do()mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"""


    "Day 03 Part 1 sample input 1" {
        val expected = 161
        day03Part1(sampleInput1) shouldBe expected
    }

    "Day 03 Part 1" {
        val expected = 174561379
        day03Part1(input) shouldBe expected
    }

    "Day 03 Part 2 sample input 2" {
        val expected = 48.toBigInteger()
        day03Part2(sampleInput2) shouldBe expected
    }

    "Day 03 Part 2 sample input 3" {
        val expected = 48.toBigInteger()
        day03Part2(sampleInput3) shouldBe expected
    }

    "Day 03 Part 2 sample input 4" {
        val expected = 48.toBigInteger()
        day03Part2(sampleInput4) shouldBe expected
    }

    "Day 03 Part 2 sample input 5" {
        val expected = 72.toBigInteger()
        day03Part2(sampleInput5) shouldBe expected
    }

    "Day 03 part 2 simplified input" {
        val expected = 106921067.toBigInteger()
        day03Part2(simplifiedInput) shouldBe expected
    }

    "Day 03 Part 2" {
        val expected = 106921067.toBigInteger()
        day03Part2(input) shouldBe expected
    }
})

private const val simplifiedInput = """mul(420,484)mul(218,461)mul(93,56)mul(162,415)mul(556,374)mul(561,946)mul(97,699)mul(387,15)mul(927,207)do()mul(454,740)mul(54,688)mul(338,694)mul(127,722)mul(337,149)mul(11,87)mul(596,125)mul(452,741)mul(513,343)mul(45,508)mul(758,167)mul(3,372)mul(491,647)mul(459,967)mul(369,467)mul(662,431)mul(172,72)mul(394,721)mul(69,419)mul(135,896)mul(692,658)mul(586,546)mul(609,19)mul(54,319)mul(962,480)mul(419,406)mul(524,802)mul(938,46)mul(443,398)mul(373,536)mul(505,931)mul(457,381)mul(800,67)mul(807,815)mul(667,529)mul(636,823)mul(363,915)mul(162,118)mul(461,357)mul(303,284)mul(31,429)do()mul(471,260)mul(706,849)mul(845,857)mul(417,923)mul(731,874)mul(433,314)mul(960,331)mul(648,668)mul(649,819)mul(857,238)mul(603,559)mul(511,89)mul(888,328)mul(177,966)mul(211,756)mul(297,394)mul(603,264)mul(794,883)mul(446,859)mul(388,763)do()mul(974,397)mul(137,814)mul(897,431)mul(74,810)mul(49,565)mul(926,812)mul(842,573)mul(126,526)mul(818,934)mul(240,118)mul(77,983)mul(736,950)mul(213,409)mul(581,188)mul(87,245)mul(558,637)mul(896,494)mul(374,932)mul(780,265)mul(218,272)mul(892,55)mul(527,984)don't()mul(56,714)mul(819,178)mul(793,717)mul(719,587)mul(919,859)mul(89,488)mul(680,657)mul(619,642)don't()mul(619,164)mul(528,754)mul(199,830)mul(32,974)do()mul(42,960)mul(802,384)mul(610,271)don't()mul(449,76)mul(944,356)mul(476,918)mul(124,698)mul(579,336)mul(953,943)mul(222,14)don't()mul(986,323)mul(476,802)mul(160,849)mul(239,985)mul(356,867)mul(486,916)do()mul(601,494)mul(549,744)mul(381,237)mul(368,89)mul(926,974)do()mul(770,708)mul(612,548)mul(651,439)mul(16,267)mul(865,452)mul(888,41)mul(655,759)do()mul(160,406)mul(392,433)mul(56,722)mul(766,7)mul(349,492)mul(12,290)mul(268,832)mul(994,952)mul(218,644)mul(231,58)mul(851,263)mul(66,566)mul(347,173)mul(266,892)mul(399,289)don't()mul(337,947)mul(556,447)mul(269,222)mul(554,881)mul(355,774)mul(900,983)mul(112,191)mul(670,76)mul(32,426)mul(160,991)mul(638,720)mul(728,694)mul(173,600)mul(846,617)mul(630,82)mul(443,274)mul(274,844)mul(69,98)mul(121,360)mul(662,319)mul(140,256)mul(690,207)do()mul(914,702)mul(881,879)mul(930,525)mul(535,202)mul(499,269)mul(855,63)mul(956,752)don't()mul(195,514)mul(69,391)mul(52,825)do()mul(287,62)mul(24,517)mul(411,475)mul(162,897)mul(985,450)mul(164,716)mul(211,564)mul(886,158)mul(215,209)mul(765,613)mul(320,608)mul(572,911)do()mul(829,161)mul(304,894)mul(279,942)mul(838,154)mul(65,418)mul(308,921)mul(355,197)mul(252,964)mul(428,463)mul(463,946)mul(545,536)do()mul(799,451)mul(685,980)mul(542,862)mul(702,259)mul(563,554)mul(819,791)do()mul(753,669)do()mul(561,517)mul(21,397)mul(585,719)mul(694,254)mul(650,398)mul(549,891)don't()mul(90,910)mul(903,233)mul(547,208)mul(368,116)mul(579,530)mul(982,408)mul(68,361)mul(316,679)mul(147,773)mul(179,83)mul(577,204)mul(360,592)mul(888,688)mul(271,431)mul(991,736)mul(520,174)mul(791,96)mul(365,155)mul(701,322)mul(677,714)mul(313,947)mul(229,775)mul(704,688)mul(437,176)mul(420,497)mul(550,773)mul(686,382)mul(540,547)mul(21,121)mul(894,561)mul(76,341)mul(867,180)mul(328,567)mul(500,955)mul(602,93)mul(395,822)mul(26,996)mul(127,564)mul(326,149)mul(741,743)mul(571,104)mul(557,104)don't()mul(243,161)mul(784,800)mul(969,40)mul(287,102)mul(581,311)mul(598,245)mul(766,217)mul(702,474)mul(454,895)do()mul(169,35)mul(478,718)mul(549,42)mul(32,8)mul(470,783)mul(354,577)don't()mul(241,993)mul(836,571)mul(746,688)mul(723,608)mul(726,41)mul(352,571)don't()mul(104,291)mul(294,381)mul(617,169)mul(597,322)mul(751,110)mul(487,642)mul(611,293)mul(572,491)mul(235,699)don't()mul(522,362)mul(808,332)mul(382,736)mul(763,385)mul(493,959)mul(999,264)mul(823,219)mul(954,651)mul(126,796)mul(200,914)mul(97,838)mul(774,947)mul(825,424)mul(645,624)mul(755,196)mul(78,987)mul(155,256)mul(643,546)mul(464,137)mul(549,85)mul(824,94)mul(232,49)mul(587,107)mul(728,869)mul(85,612)mul(992,351)mul(149,626)mul(185,920)mul(258,257)mul(891,488)mul(69,62)do()mul(163,414)mul(238,10)mul(331,689)mul(751,187)mul(114,913)mul(890,554)mul(367,963)mul(374,638)mul(651,193)do()mul(409,836)do()mul(462,596)mul(258,244)mul(862,325)mul(355,83)mul(996,680)do()mul(245,774)don't()mul(894,110)mul(28,62)mul(490,609)do()mul(446,48)mul(975,915)mul(282,257)mul(504,101)mul(799,76)mul(157,178)mul(136,339)mul(424,206)mul(855,735)mul(422,548)mul(403,999)mul(262,876)mul(304,364)mul(923,176)mul(431,469)mul(330,297)mul(92,961)mul(91,266)mul(875,759)mul(214,511)mul(506,250)mul(67,306)do()mul(87,147)mul(433,7)don't()mul(572,795)mul(428,863)mul(408,652)mul(940,728)mul(246,59)mul(498,374)mul(489,557)mul(737,804)mul(328,754)mul(72,123)mul(575,80)mul(490,263)mul(491,393)do()mul(633,858)mul(126,973)mul(670,112)mul(353,742)mul(47,808)mul(774,434)mul(386,975)mul(790,566)mul(542,715)mul(935,548)mul(164,558)mul(165,31)mul(341,516)mul(162,61)mul(571,437)mul(522,596)mul(467,537)mul(974,626)mul(424,960)mul(332,298)mul(613,817)mul(632,893)mul(211,720)mul(833,547)mul(614,72)mul(324,924)mul(679,438)mul(261,94)mul(531,950)mul(387,794)mul(732,253)mul(464,965)mul(970,460)mul(130,649)mul(594,237)mul(715,98)mul(949,543)mul(341,112)mul(802,786)mul(991,224)mul(187,549)mul(780,836)mul(650,459)mul(520,113)mul(616,814)mul(759,564)mul(716,211)mul(347,498)mul(46,914)mul(119,463)mul(281,195)mul(945,971)mul(187,286)don't()mul(156,64)mul(113,825)mul(13,265)mul(66,66)mul(132,363)mul(373,957)mul(319,15)do()mul(597,465)mul(836,692)mul(635,341)mul(279,997)mul(823,590)mul(822,370)mul(953,604)mul(535,413)mul(484,759)mul(871,854)mul(373,823)mul(159,820)mul(799,36)mul(315,321)mul(548,25)mul(358,469)mul(281,747)mul(190,780)mul(353,297)mul(578,187)mul(414,374)mul(203,987)mul(441,105)mul(358,845)mul(886,76)mul(675,177)mul(380,191)mul(430,140)mul(619,859)mul(606,253)mul(40,538)mul(989,612)mul(290,587)mul(916,886)mul(743,103)don't()mul(164,643)mul(615,169)mul(643,22)mul(551,479)mul(444,127)mul(31,673)mul(864,803)do()mul(668,272)mul(490,117)don't()mul(431,252)mul(928,524)mul(508,343)mul(14,808)mul(247,330)mul(759,514)mul(438,583)mul(278,317)mul(25,446)mul(379,907)mul(216,783)do()mul(378,492)mul(799,619)mul(535,785)mul(140,345)mul(183,163)mul(950,595)mul(938,511)mul(659,791)mul(339,535)mul(57,448)mul(863,872)mul(121,155)mul(913,601)mul(697,273)don't()mul(757,595)mul(818,23)mul(649,864)mul(342,657)mul(694,462)mul(697,789)don't()mul(630,85)do()mul(491,525)mul(166,732)mul(343,571)mul(662,576)mul(387,826)mul(363,451)mul(393,911)mul(235,163)mul(107,703)mul(834,654)mul(232,808)mul(140,807)mul(65,22)mul(501,321)mul(105,411)mul(94,110)mul(926,669)mul(143,338)mul(835,604)mul(906,637)mul(725,278)mul(14,283)don't()mul(578,235)mul(186,328)mul(554,733)mul(138,929)mul(25,167)mul(203,77)mul(216,980)mul(195,953)mul(481,558)mul(405,524)mul(486,66)mul(328,209)mul(686,850)mul(604,374)mul(68,860)mul(548,321)mul(276,13)mul(692,25)mul(366,243)mul(651,369)mul(900,577)mul(691,190)do()mul(370,234)mul(712,897)mul(579,611)mul(840,718)mul(53,189)mul(438,763)don't()mul(641,81)mul(938,975)mul(747,483)mul(817,775)mul(273,96)mul(734,544)mul(997,378)mul(857,880)mul(938,942)mul(552,726)mul(753,798)mul(465,371)mul(423,396)mul(677,239)mul(341,814)don't()mul(303,894)mul(100,628)mul(481,789)mul(420,12)do()mul(866,134)mul(971,422)mul(425,573)do()mul(777,75)mul(22,151)mul(983,529)mul(4,608)mul(138,543)mul(646,239)mul(986,403)mul(981,986)mul(744,394)mul(93,727)mul(424,603)mul(297,910)mul(860,951)mul(431,588)mul(8,460)mul(556,634)mul(588,147)mul(499,831)mul(475,184)mul(696,749)mul(294,115)mul(409,579)mul(541,209)mul(612,20)mul(727,495)mul(174,985)mul(420,889)mul(350,415)mul(408,995)mul(460,467)mul(133,225)mul(587,173)mul(622,327)mul(684,509)mul(143,815)mul(929,986)mul(119,51)mul(50,335)mul(660,21)mul(330,503)mul(597,951)mul(936,959)mul(585,125)mul(583,688)mul(77,828)mul(901,180)mul(359,130)mul(732,295)mul(686,46)mul(27,581)mul(375,566)mul(177,377)mul(511,997)mul(21,362)mul(718,676)mul(87,923)mul(988,768)mul(198,629)mul(330,891)mul(810,309)mul(950,136)mul(669,771)mul(70,983)mul(283,218)mul(920,709)mul(447,444)mul(514,715)mul(616,746)mul(819,532)mul(203,692)mul(75,664)mul(506,516)mul(967,985)mul(252,812)mul(69,311)mul(748,794)mul(355,985)mul(150,45)mul(249,182)don't()mul(286,220)mul(297,443)mul(35,888)mul(417,143)mul(910,829)mul(715,749)mul(565,17)mul(576,280)mul(184,166)mul(569,96)mul(994,408)mul(443,685)mul(372,670)mul(376,74)mul(457,809)mul(442,658)mul(503,506)mul(66,112)mul(87,307)mul(621,443)don't()mul(674,840)mul(921,417)don't()mul(221,145)mul(777,637)mul(945,14)mul(816,83)mul(195,318)mul(795,512)mul(42,828)mul(234,453)mul(885,717)mul(91,905)mul(437,42)don't()mul(422,930)mul(401,54)mul(691,931)mul(442,585)mul(842,765)mul(62,265)mul(194,966)mul(894,538)mul(183,809)mul(660,286)mul(355,338)mul(562,357)mul(897,481)mul(289,64)mul(251,282)mul(291,786)mul(903,286)mul(409,717)mul(75,576)mul(509,51)mul(910,246)mul(325,400)mul(303,777)mul(701,365)mul(789,577)mul(620,292)"""
