# NetworkDemo
retrofit+okhttp+rxjava+mvp网络框架基本模式
## 架构模式
为了更好的解耦，在model层和presenter层之间再次抽象出一个业务层。
model层为数据模型层。
业务层在model层上再次抽象，用于对源数据进行过滤和去壳，已经网络异常和数据异常状态的处理。
presenter层只关心有效数据的处理。
view层功能不变。
